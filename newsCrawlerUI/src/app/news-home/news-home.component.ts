import { Component, OnInit } from '@angular/core';
import { NewsEntity } from '../Entity/newsEntity';
import { NewsServiceService } from '../service/news-service.service';
import { Observable } from '../../../node_modules/rxjs';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-news-home',
  templateUrl: './news-home.component.html',
  styleUrls: ['./news-home.component.css']
})
export class NewsHomeComponent implements OnInit {ß

  news$: Observable<Array<NewsEntity>>;
  searchDate: any;
  dayBefore: any;
  dayAfter: any;

  constructor(private _svc: NewsServiceService, private datePipe: DatePipe) { }

  ngOnInit() {
    this.searchDate = this.datePipe.transform(new Date(), "yyyy-MM-dd")
    this.getNews();
  }

  getNews(): void {
    console.log(this.searchDate);
    this.news$ = this._svc.getNews(this.searchDate);
  }

  addDate(){
    const addDate = new Date(this.searchDate);
    console.log(addDate);
    addDate.setDate(addDate.getDate()+2);
    this.searchDate = this.datePipe.transform(addDate, "yyyy-MM-dd")
    this.getNews();
  }
  subDate(){
    const newDate = new Date(this.searchDate);
    console.log(newDate);
    //newDate.setDate(newDate.getDate()-1);
    //console.log(newDate);
    this.searchDate = this.datePipe.transform(newDate, "yyyy-MM-dd")
    this.getNews();
  }

}
