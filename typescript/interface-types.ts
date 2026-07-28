// interface và type alias

// interface: định nghĩa 1 kiểu dữ liệu của đối tượng: User, Product, Order,...
interface User {
    id: number;
    name: string;
    email: string;
    age?: number; // ? nghĩa là optional, có cũng được, ko có cũng được
    readonly createdAt: Date; // readonly nghĩa là chỉ đọc, ko thể gán giá trị mới
}

let user1: User = {
    id: 1,
    name: "Phuong",
    email: "phuong@gmail.com",
    // age: 20,
    createdAt: new Date()
}
// các thuộc tính của User nếu ko có dấu ? thì bắt buộc phải có value
// user1.createdAt = new Date() // lỗi: Cannot assign to 'createdAt' because it is a read-only property.
console.log(user1)

// interface có thể kế thừa interface khác
interface Admin extends User {
    role: string;
}

let admin1: Admin = {
    id: 2,
    name: "Huy",
    email: "huy@gmail.com",
    role: "admin",
    createdAt: new Date()
}

// type alias
type ID = number | string; // type alias có thể là union type

interface Product {
    id: ID;
    name: string;
    price: number;
    status: "available" | "out of stock"; // union type
}

let product1: Product = {
    id: 1,
    name: "Iphone 14",
    price: 2000,
    status: "available"
}

// generic type: kiểu dữ liệu tổng quát, có thể là bất kỳ kiểu dữ liệu nào
// dùng kiểu này trong việc define kiểu dữ liệu của API response
interface ApiResponse<T> {
    status: number;
    message: string;
    data: T;
}

let userResponse: ApiResponse<User> = {
    status: 200,
    message: "Success",
    data: user1
}
console.log(userResponse)

let productResponse: ApiResponse<Product> = {
    status: 200,
    message: "Success",
    data: product1
}
console.log(productResponse)

