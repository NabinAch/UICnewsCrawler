import { Component, OnInit, Input } from '@angular/core';
import { NewsEntity } from '../Entity/newsEntity';

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.css']
})
export class NewsComponent implements OnInit {

  @Input() news : NewsEntity;

  constructor() { }

  ngOnInit() {
  }

}
