import {Component, OnInit, SimpleChanges, ViewChild} from '@angular/core';
import {MatDialog, MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {DialogComponent} from "../dialog/dialog.component";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Product} from "../model/product";
import {Category} from "../model/category";
import {ProductServiceService} from '../service/product/product-service.service';
import {CategoryServiceService} from "../service/product/category-service.service";
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  displayedColumns: string[] = [ 'name', 'price','quantity', 'category', 'description','image', 'action'];
  dataSource!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;


  public formProduct !: FormGroup;


  public products!: Product[];
  public product!: Product;

  public categories!: Category[];

  constructor(private dialog: MatDialog,
              private formBuilder: FormBuilder,
              private productService: ProductServiceService,
              private categoryService: CategoryServiceService,
              ) {
  }

  ngOnInit(): void {
    this.formProduct = this.formBuilder.group({
      id: [''],
      name: [''],
      price: [''],
      quantity: [''],
      description: [''],
      category: [''],
      image: ['']
    })
    this.getAllCategory();
   this.displayAllProduct()
  }

  openDialog() {
    this.dialog.open(DialogComponent, {
      width: '30%'
    }).afterClosed().subscribe(()=>{
      console.log(this.displayAllProduct())
        this.displayAllProduct();
    });
  }

  public getAllCategory() {
    this.categoryService.getAllCategory().subscribe(data => {
      this.categories = data
    })
  }


  public displayAllProduct(){
    this.productService.getAllProduct().subscribe({
      next:(res)=>{
        this.products = res
        this.dataSource = new MatTableDataSource(res)
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      }, error:(err)=>{
        alert('Error while searching product')
      }
    })
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  public editProduct1(row:any ){
    this.dialog.open(DialogComponent,{
      width: '30%',
      data: row
    }).afterClosed().subscribe(() => {
      this.displayAllProduct();

  })}




  public getProduct(id: any) {
    this.productService.getProductById(id).subscribe((data) => {
      this.products = [];
      this.products.push(data);
    });

  }

  public deleteProduct(id: number) {
    if (confirm('Are you sure delete product: ' + '?')) {
      this.productService.deleteProduct(id).subscribe(() => {
          alert('Delete Successfully!');
          this.displayAllProduct()

        }
      );
    }
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.displayAllProduct()
  }

}
