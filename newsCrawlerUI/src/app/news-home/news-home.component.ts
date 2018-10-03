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
export class NewsHomeComponent implements OnInit {

  news$: Observable<Array<NewsEntity>>;
  searchDate: any;
  dateValue: any;

  datePickerConfig: Partial<BsDatepickerConfig>;

  constructor(private _svc: NewsServiceService, private datePipe: DatePipe) {
    this.datePickerConfig = Object.assign({},
      {
        containerClass: 'theme-dark-blue',
        showWeekNumbers: false,
        minDate: new Date(new Date().setDate(new Date().getDate() - 30)),
        maxDate: new Date(),
        dateInputFormat: 'YYYY-MM-DD'
      })
  }

  ngOnInit() {
    this.searchDate = this.datePipe.transform(new Date(), "yyyy-MM-dd");
    this.dateValue = this.searchDate;
    this.getNews();
  }

  getNews(): void {
    this.news$ = this._svc.getNews(this.searchDate);
  }

  addDate() {
    const addDate = new Date(this.searchDate);
    addDate.setDate(addDate.getDate() + 1)
    if (addDate <= new Date()) {
      addDate.setDate(addDate.getDate() + 1);
      this.dateValue = this.datePipe.transform(addDate, "yyyy-MM-dd")
      }
  }
  subDate() {
    const newDate = new Date(this.searchDate);
    if (newDate >= new Date(new Date().setDate(new Date().getDate() - 30))) {
      this.dateValue = this.datePipe.transform(newDate, "yyyy-MM-dd")
      }
  }

  selectDate() {
    this.searchDate = this.datePipe.transform(this.dateValue, "yyyy-MM-dd")
   this.getNews();
  }

}
