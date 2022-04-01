import {Component, Inject, OnInit} from '@angular/core';
import {CategoryServiceService} from "../service/product/category-service.service";
import {Category} from "../model/category";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ProductServiceService} from "../service/product/product-service.service";
import {MatDialogRef} from "@angular/material/dialog";
import {MatDialog, MAT_DIALOG_DATA} from '@angular/material/dialog';
import {finalize, Observable} from "rxjs";
import {AngularFireStorage} from "@angular/fire/compat/storage";
import {LoginComponent} from "../login/login/login.component";


@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class DialogComponent implements OnInit {

  productForm !: FormGroup;
  public categories!:Category[];
  public description = ['New', 'Like New 98%', 'Old']
  title = "cloudsSorage";
  selectedFile?: File;
  fb: any = '';
  downloadURL?: Observable<string>;

  ngOnInit(): void {
    this.productForm = this.formBuilder.group({
      id:[''],
      name : ['',[Validators.required]],
      price: ['',[Validators.required]],
      quantity:['', [Validators.required]],
      description: ['', [Validators.required]],
      category : ['', [Validators.required] ],
      image: ['',]
    })
    this.getAllCategory()

    if(this.editData){
      this.productForm.controls['id'].setValue(this.editData.id);
      this.productForm.controls['name'].setValue(this.editData.name);
      this.productForm.controls['price'].setValue(this.editData.price);
      this.productForm.controls['quantity'].setValue(this.editData.quantity);
      this.productForm.controls['category'].setValue(this.editData.category.id);
      this.productForm.controls['description'].setValue(this.editData.description);
      this.productForm.controls['image'].setValue(this.editData.image);
    }

  }

  constructor(private categoryService:CategoryServiceService,
              private formBuilder:FormBuilder,
              @Inject(MAT_DIALOG_DATA) public editData: any,
              private productService:ProductServiceService,
              private matDialogRef: MatDialogRef<DialogComponent>,
              private storage: AngularFireStorage) {
  }

  public getAllCategory(){
    this.categoryService.getAllCategory().subscribe(data =>{
      this.categories = data
    })
  }


  public createProduct() {
     const product = {
       id: this.productForm.value.id,
       name: this.productForm.value.name,
       price: this.productForm.value.price,
       quantity: this.productForm.value.quantity,
       description: this.productForm.value.description,
       category: {id: this.productForm.value.category},
       image: this.fb
     };
     this.productService.createProduct(product).subscribe(() => {
       alert('Create Successfully');
       console.log(product)
       console.log(this.fb)
       this.productForm.reset();
       this.matDialogRef.close();
     });

  }

  // public updateProduct(){
  //   this.productService.putProduct(this.productForm.value, this.editData.id)
  //     .subscribe({
  //       next:(res)=>{
  //         console.log(res)
  //         alert('Update Successfully');
  //         this.productForm.reset();
  //         this.matDialogRef.close('update')
  //       }, error:()=>{
  //         alert("error while update")
  //       }
  //     })
  // }


  onFileSelected(event:any) {
    var n = Date.now();
    const file = event.target.files[0];
    const filePath = `RoomsImages/${n}`;
    const fileRef = this.storage.ref(filePath);
    const task = this.storage.upload(`RoomsImages/${n}`, file);
    task
      .snapshotChanges()
      .pipe(
        finalize(() => {
          this.downloadURL = fileRef.getDownloadURL();
          this.downloadURL.subscribe(url => {
            if (url) {
              this.fb = url;
            }
          });
        })
      )
      .subscribe();
  }

}
