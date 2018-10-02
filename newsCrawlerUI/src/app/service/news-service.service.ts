import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { NewsEntity } from '../Entity/newsEntity';
import { Observable } from '../../../node_modules/rxjs';

@Injectable({
  providedIn: 'root'
})
export class NewsServiceService {

  apiAddress: string;
  constructor(private _http: HttpClient) {
    this.apiAddress='http://localhost:8080/news?date=';
   }

  getNews(searchDate:any): Observable<Array<NewsEntity>>{
    return this._http.get<Array<NewsEntity>>(this.apiAddress+searchDate);
  }
}
