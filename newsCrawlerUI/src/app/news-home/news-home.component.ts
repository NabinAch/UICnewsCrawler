import { Component, OnInit } from '@angular/core';
import { NewsEntity } from '../Entity/newsEntity';
import { NewsServiceService } from '../service/news-service.service';
import { Observable } from '../../../node_modules/rxjs';
import { DatePipe } from '@angular/common';
import { BsDatepickerConfig } from 'ngx-bootstrap/datepicker'


@Component({
  selector: 'app-news-home',
  templateUrl: './news-home.component.html',
  styleUrls: ['./news-home.component.css']
})
export class NewsHomeComponent implements OnInit {ß

  news$: Observable<Array<NewsEntity>>;
  searchDate: any;
  dayBefore: boolean = false;
  dayAfter: boolean = false;

  datePickerConfig: Partial<BsDatepickerConfig>;

  constructor(private _svc: NewsServiceService, private datePipe: DatePipe) {
    this.datePickerConfig = Object.assign({},
    {
      containerClass: 'theme-dark-blue',
      showWeekNumbers: false,
      minDate: new Date(new Date().setDate(new Date().getDate()-30)),
      maxDate: new Date(),
      dateInputFormat: 'YYYY-MM-DD'
    })
   }

  ngOnInit() {
    this.searchDate = this.datePipe.transform(new Date(), "yyyy-MM-dd")
    this.getNews();
  }

  getNews(): void {
    //console.log(this.searchDate);
    //this.searchDate = this.datePipe.transform(new Date(this.searchDate), "yyyy-MM-dd")
    this.news$ = this._svc.getNews(this.searchDate);
  }

  addDate(){
    const addDate = new Date(this.searchDate);
    addDate.setDate(addDate.getDate()+1)
    this.dayAfter = true;
   if(addDate<=new Date())
   {
    this.dayAfter = true;
    addDate.setDate(addDate.getDate()+1);
    this.searchDate = this.datePipe.transform(addDate, "yyyy-MM-dd")
    this.getNews();
   }
  }
  subDate(){
    const newDate = new Date(this.searchDate);
    console.log(newDate);
    this.searchDate = this.datePipe.transform(newDate, "yyyy-MM-dd")
    this.getNews();
  }

}
