import { Component, OnInit } from '@angular/core';
import {Category} from "../model/category";
import {FormBuilder, FormGroup, Validator, Validators} from "@angular/forms";

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {
  public categories!:Category[];

  constructor() { }

  ngOnInit(): void {

  }

}
