import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Libro } from '../model/libro.model';

@Injectable({
  providedIn: 'root'
})
export class LibroService {
  private baseURL = "http://localhost:8080/api/libros";

  constructor(private http: HttpClient) { }

  findAll(): Observable<Libro[]> {
    return this.http.get<Libro[]>(this.baseURL);
  }

  findOne(id: number): Observable<Libro> {
    return this.http.get<Libro>(`${this.baseURL}/${id}`);
  }

  save(libro: Libro): Observable<Libro> {
    return this.http.post<Libro>(this.baseURL, libro);
  }

  update(id: number, libro: Libro): Observable<Libro> {
    return this.http.put<Libro>(`${this.baseURL}/${id}`, libro);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseURL}/${id}`);
  }
}
