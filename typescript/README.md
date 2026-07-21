java -> pom.xml
typescript/javascript -> package.json

nodejs CHỈ CHẠY ĐƯỢC VỚI FILE .JS
typescript .ts => cài thư viện (lib) để hỗ trợ convert
.ts -> .js

frontend => js (reactjs, vuejs, angular)

ĐỂ CÀI THƯ VIỆN
npm i typescript ......
npm install

- typescript - compiler chính giúp chạy được file .ts trên môi trường nodejs
- @types/node - typescript sẽ không tự biết các hàm của javascript: process, setTimeout,... nên phải cài lib này để typescript hiểu những hàm này ở đâu
giúp cho typescript không báo lỗi trên IDE
- tsx: chạy file .ts trên terminal
VD: npx tsx abc.ts

- SAU KHI cài xong lib => node tạo folder node_modules
LƯU Ý: PHẢI folder này thì mới chạy được project code bằng typescript/javascript

khi push code lên github/gitlab/bitbucket NHỚ LÀ IGNORE folder node_modules vì
- node_modules sẽ code code lib riêng cho hệ điều hành
- folder này nặng (vài trăm MB -> vài GB)

TRƯỜNG HỢP LẤY SOURCE CODE TỪ GITHUB VỀ MÀ KHÔNG CÓ FOLDER NODE_MODULES
=> CHỈ CẦN DÙNG CÂU LỆNH: npm i => auto tạo folder node_modules