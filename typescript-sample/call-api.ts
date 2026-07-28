import axios from "axios"

const API_URL = "https://movie0706.cybersoft.edu.vn/"

// interface Movie
// {
//     "maPhim": 11214,
//     "tenPhim": "Loki1231",
//     "biDanh": "loki1231",
//     "trailer": "",
//     "hinhAnh": "http://movie0706.cybersoft.edu.vn/hinhanh/loki_gp01.png",
//     "moTa": "The mercurial villain Loki resumes his role as the God of Mischief in a new series that takes place after the events of “Avengers: Endgame.”",
//     "maNhom": "GP01",
//     "ngayKhoiChieu": "2020-10-10T00:00:00",
//     "danhGia": 10
//   }
interface Movie {
    maPhim: number,
    tenPhim: string,
    biDanh: string,
    trailer: string,
    hinhAnh: string,
    moTa: string,
    maNhom: string,
    ngayKhoiChieu: string,
    danhGia: number
}

// hàm call api get movie
const getListMovie = async (): Promise<void> => {
    // dùng axios để call api get movie
    // URL api: <BASE_URL><ENDPOINT CỦA TỪNG API>
    const API_LIST_MOVIE = `${API_URL}api/QuanLyNguoiDung/LayDanhSachNguoiDung?MaNhom=GP01`
    try {
        const response = await axios.get<Movie[]>(API_LIST_MOVIE)
        // axios: dữ liệu trả về từ api được lưu trong response.data
        console.log(response.data)
    } catch (error) {
        console.log(error)
    }
}
// gọi hàm getListMovie
getListMovie()
// sau này dùng call api từ playwright
// syntax: request.get(url, options) => trả về response