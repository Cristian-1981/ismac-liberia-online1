import { Component, ElementRef, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { Libro } from '../../model/libro.model';
import { Autor } from '../../model/autor.model';
import { Categoria } from '../../model/categoria.model';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { LibroService } from '../../services/libro';
import { AutorService } from '../../services/autor';
import { CategoriaService } from '../../services/categoria';
import { MatDialog } from '@angular/material/dialog';
import { HttpClient } from '@angular/common/http';
import Swal from 'sweetalert2';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-libro',
  standalone: false,
  templateUrl: './libro.html',
  styleUrl: './libro.css'
})
export class LibroComponent implements OnInit {

    libros: Libro[] = [];
    autores: Autor[] = [];
    categorias: Categoria[] = [];
    libro: Libro = {} as Libro;
    editar: boolean = false;
    idEditar: number | null = null;
    dataSource!: MatTableDataSource<Libro>;
    selectedFile!: File;
    imagenPreview: string = "";
    libroSeleccionado: Libro | null = null;

    mostrarColumnas: string[] = ['detalles','idLibro', 'titulo', 'editorial','edicion','idioma','fechaPublicacion',
                                'numEjemplares','precio','autor', 'categoria',  'acciones'];
    
    @ViewChild('formLibro') formularioLibro!: ElementRef;
    @ViewChild(MatPaginator) paginator!: MatPaginator;
    @ViewChild(MatSort) sort!: MatSort;
    @ViewChild('modalLibro') modallibro!: TemplateRef<any>;
    @ViewChild('modalDetalles') modalDettales!: TemplateRef<any>;


    constructor(
    private libroService: LibroService,
    private autorService: AutorService,
    private categoriaService: CategoriaService,
    private dialog: MatDialog,
    private http: HttpClient
    ) { 
    }

  ngOnInit(): void {
    this.findAll();
    this.cargarAutores();
    this.cargarCategorias();
  }

  findAll(): void {
    this.libroService.findAll().subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  cargarAutores(): void {
    this.autorService.findAll().subscribe(data=>{
      this.autores = data;
    });
  }

  cargarCategorias(): void {
    this.categoriaService.findAll().subscribe(data=>{
      this.categorias = data;
    });
  }

  save(): void {
    this.libroService.save(this.libro).subscribe(() => {
      this.libro = { } as Libro;
      this.findAll();
    });
  }

  update(): void {
    if (this.idEditar != null) {
      this.libroService.update(this.idEditar, this.libro).subscribe(() => {
        this.libro = { } as Libro;
      this.findAll();
      });
    }
  }
  
  delete(id: number): void {
    Swal.fire({
      title: '¿Desea eliminar el libro?',
      text: 'Esta acción no se puede deshacer',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sí, eliminar',
      cancelButtonText: 'Cancelar',
      confirmButtonColor: '#d33',
      cancelButtonColor: '#3085d6'
    }).then((result) => {
      if (result.isConfirmed) {
        this.libroService.delete(id).subscribe(() => {
          this.findAll();
          Swal.fire('Eliminado', 'El libro ha sido eliminado.', 'success');
        });
      }else{
        this.libro = { } as Libro;
      }
    });
  }

  // interactuar con la pagina web

  editarLibro(libro: Libro): void {
    this.libro = { ...libro };
    this.idEditar = libro.idLibro;
    this.editar = true;
    setTimeout(() =>{
      this.formularioLibro.nativeElement.scrollIntoView({ behavior: 'smooth', lock: 'start' });
    }, 100);
  }

  editarLibroCancelar(form: NgForm): void {
    this.libro = { } as Libro;
    this.idEditar = null;
    this.editar = false;
    form.resetForm();
  }

  guardarLibro(): void{
    if (this.editar && this.idEditar !== null) {
      this.update();
    } else {
      this.save();
    }
    this.dialog.closeAll();
  }

  filtroLibro(event: Event): void {
    const filtro = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filtro.trim().toLowerCase();
  }

  nombreCompletoAutor(autor: Autor): string {
    return `${autor.nombre} ${autor.apellido}`;
  }

  abrirModal(libro?: Libro): void {
    if (libro) {
      this.libro = { ...libro };
      this.editar = true;
      this.idEditar = libro.idLibro;
  } else {
      this.libro = {} as Libro;
      this.editar = false;
      this.idEditar = null;
    }

    this.dialog.open(this.modallibro, {
      width: '800px',
      disableClose: true
    });
  }

  compararAutores(a1: Autor, a2: Autor): boolean {
    return a1 && a2 ? a1.idAutor == a2.idAutor : a1 == a2;
  }

  compararCategorias(c1: Categoria, c2: Categoria): boolean {
    return c1 && c2 ? c1.idcategoria == c2.idcategoria : c1 == c2;
  }

  seleccionarArchivo(event: any) {
    this.selectedFile = event.target.files[0];
  }

  subirImagen(): void {
    const formData = new FormData();
    formData.append("file", this.selectedFile);

    if (this.libro.portada) {
      formData.append("oldImage", this.libro.portada);
    }

    this.http.post<{ ruta: string }>('http://localhost:8080/api/upload-portada', formData)
          .subscribe((res) => {
            this.libro.portada = res.ruta;
            this.imagenPreview = res.ruta;
          });
  }

  abrirModalDetalles(libro: Libro): void {
    this.libroSeleccionado = libro;
    this.dialog.open(this.modalDettales, {
      width: '500px'
    });
  }

  cerrarModal(): void {
    this.dialog.closeAll();
    this.libroSeleccionado = null;
  }

}
