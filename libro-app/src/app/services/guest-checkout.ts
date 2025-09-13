import { Injectable } from '@angular/core';
import { enviroment } from '../../environments/environments';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { getCartToken } from '../core/cart-token';
import { Factura } from '../model/factura.model';

@Injectable({
  providedIn: 'root'
})
export class GuestCheckoutService {

  private base = `${enviroment.baseUrl}/guest/checkout`;

constructor(private http: HttpClient) { }

checkout(): Observable<Factura> {
  const params = new HttpParams().set('token', getCartToken());
  return this.http.post<Factura>(this.base, {}, { params });
}
  
}
