// utility type hỗ trợ tạo các loại dữ liệu mới dựa vào interface đã có sẵn
// Partial, Omit, Pick, Record
// 1. Partial: chuyển đổi các thuộc tính của interface thành optional
// Partial dùng trong các API update dữ liệu
interface User {
    id: number;
    name: string;
    email: string;
    password: string;
}

type UpdateUserPayload = Partial<User>

const updateUser: UpdateUserPayload = {
    email: "newemail@example.com",
    password: "newpassword"
}

// 2. Omit: loại bỏ các thuộc tính của interface
type UserIgnorePassword = Omit<User, "password">
// lợi ích: khi api trả về dữ liệu user, dùng Omit để loại field password
// trước khi hiển thị ra màn hình, tránh lộ thông tin nhạy cảm
const secureUser: UserIgnorePassword = {
    id: 1,
    name: "John Doe",
    email: "john@gmail.com"
}
console.log(secureUser)

// 3. Pick: chọn ra các thuộc tính của interface
// lợi ích: chỉ lấy những field cần thiết, tránh tạo lại interface mới
// VD: login, register
type UserLoginPayload = Pick<User, "email" | "password">