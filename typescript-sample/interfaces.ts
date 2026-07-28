// tạo 1 object interface Product gồm các thuộc tính
// id, name, price, description
// yêu cầu:
// viết hàm nhận vào 1 mảng Product. Hàm này in ra các sản phẩm lớn 100.000

interface Product {
    id: number;
    name: string;
    price: number;
    description?: string;
}

const items: Product[] = [
    {id: 1, name: 'Product 1', price: 50000, description: 'Description 1'},
    {id: 2, name: 'Product 2', price: 150000, description: 'Description 2'},
    {id: 3, name: 'Product 3', price: 200000, description: 'Description 3'},
]

const printExpensiveProducts = (products: Product[]): void => {
    const expensiveProducts = products.filter(product => product.price > 100000);
    expensiveProducts.forEach(product => console.log(`ID: ${product.id}, Name: ${product.name}, Price: ${product.price}, Description: ${product.description}`));
}

printExpensiveProducts(items);

// generic: đại diên cho kiểu dữ liệu bất kỳ: number, string, boolen, object,...
// automation test: viết hàm assertEqual nhận vào 3 tham số:
// array: T[], item: T, testName: string
// sample: array string, array number, array object

const assertEqual = <T>(array: T[], item: T, testName: string): void => {
    // hàm kiểm tra xem item có tồn tại trong array hay không
    // includes để kiểm tra item có tồn tại trong array hay không
    if (array.includes(item)) {
        console.log(`Passed: ${testName}`);
    } else {
        console.log(`Failed: ${testName}`);
    }
}
const sample1: string[] = ["admin", "user", "guest"];
assertEqual(sample1, "admin", "Test 1");
assertEqual(sample1, "manager", "Test 2");

const sample2: number[] = [1, 2, 3, 4, 5];
assertEqual(sample2, 3, "Test 3");
assertEqual(sample2, 6, "Test 4");