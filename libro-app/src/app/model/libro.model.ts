    import { Categoria } from "../categoria";
    import { Autor } from "./autor.model";

    export interface Libro {
        idLibro: number;
        titulo: string;
        editorial: string;
        numpaginas: number;
        edicion: string;
        idioma: string;
        fechapublicacion: Date;
        descripcion: string;
        tipopasta: string;
        isbn: string;
        numejemplares: number;
        portada: string;
        presentacion: string;
        precio: number;
        categoria: Categoria
        autor: Autor

        [key: string]: any;

    }