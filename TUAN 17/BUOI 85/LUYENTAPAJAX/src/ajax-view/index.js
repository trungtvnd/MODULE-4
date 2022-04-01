let index = 0;

function getProduct(){
    $.ajax({
        type: "GET",
        url: `http://localhost:8080/api/products`,
        success: function (data){
            let content = "";
            content+= `        <tr>
            <th>Name</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Description</th>
            <th>Category</th>
            <th colspan="2">Action</th>
        </tr>`
            for (let i = 0; i < data.length; i++) {
                content+=`        <tr>
            <td>${data[i].name}</td>
            <td>${data[i].price}</td>
            <td>${data[i].quantity}</td>
            <td>${data[i].description}</td>
            <td>${data[i].category.name}</td>
            <td><button class="btn btn-danger" onclick="deleteProduct(${data[i].id})">Delete</button></td>
            <td><button class="btn btn-danger" onclick="editProduct(${data[i].id})">Edit</button></td>
        </tr>`
            }
            document.getElementById("productList").innerHTML = content;
        }
    })
}
getProduct()
