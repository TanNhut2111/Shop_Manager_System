-- ===================================================
-- DATABASE: Quản lý cửa hàng giày (phiên bản mở rộng)
-- Ngày tạo: 08 Nov 2025
-- ===================================================
CREATE DATABASE qlcuahanggiaydb;
USE qlcuahanggiaydb;

-- ======================= BẢNG tblchitiethd =======================
CREATE TABLE tblchitiethd (
  Magiay nvarchar(20) NOT NULL,
  MaHD nvarchar(20) NOT NULL,
  SoLuong int NOT NULL,
  GiaBan int NOT NULL,
  deleted TINYINT DEFAULT 0
);

INSERT INTO tblchitiethd (Magiay, MaHD, SoLuong, GiaBan, deleted) VALUES
(N'SP001', N'HD001', 1, 100, 0),
(N'SP002', N'HD002', 1, 150, 0),
(N'SP003', N'HD003', 1, 200, 0),
(N'SP004', N'HD004', 1, 250, 0),
(N'SP007', N'HD005', 1, 500, 0),
(N'SP009', N'HD005', 2, 350, 0),
(N'SP008', N'HD006', 1, 700, 0),
(N'SP010', N'HD006', 1, 600, 0);

-- ======================= BẢNG tblchitietkm =======================
CREATE TABLE tblchitietkm (
  MaGiay nvarchar(20) NOT NULL,
  MaKM nvarchar(20) NOT NULL,
  TiLeKM float NOT NULL, 
  deleted TINYINT DEFAULT 0
);

INSERT INTO tblchitietkm (MaGiay, MaKM, TiLeKM, deleted) VALUES
(N'SP001', N'KM001', 0.1, 0),
(N'SP002', N'KM002', 0.5, 0),
(N'SP003', N'KM003', 0.1, 0),
(N'SP004', N'KM004', 0.3, 0),
(N'SP005', N'KM005', 0.5, 0),
(N'SP007', N'KM002', 0.2, 0),
(N'SP008', N'KM003', 0.15, 0),
(N'SP009', N'KM004', 0.25, 0),
(N'SP010', N'KM005', 0.1, 0);

-- ======================= BẢNG tblchitietpn =======================
CREATE TABLE tblchitietpn (
  MaGiay nvarchar(20) NOT NULL,
  MaPN nvarchar(20) NOT NULL,
  SoLuong int NOT NULL,
  GiaNhap int NOT NULL,
  deleted TINYINT DEFAULT 0
);

INSERT INTO tblchitietpn (MaGiay, MaPN, SoLuong, GiaNhap, deleted) VALUES
(N'SP001', N'PN001', 10, 50, 0),
(N'SP002', N'PN001', 10, 100, 0),
(N'SP003', N'PN002', 10, 150, 0),
(N'SP004', N'PN002', 10, 200, 0),
(N'SP005', N'PN003', 10, 250, 0),
(N'SP006', N'PN003', 10, 300, 0),
(N'SP007', N'PN004', 15, 400, 0),
(N'SP008', N'PN004', 10, 500, 0),
(N'SP009', N'PN004', 12, 300, 0),
(N'SP010', N'PN004', 8, 450, 0);

-- ======================= BẢNG tblhoadon =======================
CREATE TABLE tblhoadon (
  MaHD nvarchar(20) NOT NULL,
  MaNV nvarchar(20) NOT NULL,
  MaKH nvarchar(20) NOT NULL,
  MaKM nvarchar(20) NOT NULL,
  NgayBan nvarchar(20) NOT NULL,
  TongTien float NOT NULL,
  deleted TINYINT DEFAULT 0
);

INSERT INTO tblhoadon (MaHD, MaNV, MaKH, MaKM, NgayBan, TongTien, deleted) VALUES
(N'HD001', N'admin', N'KH1', N'KM001', N'25 Oct 2025', 300, 0),
(N'HD002', N'admin', N'KH2', N'KM002', N'25 Oct 2025', 550, 0),
(N'HD003', N'admin', N'KH3', N'KM003', N'25 Oct 2025', 520, 0),
(N'HD004', N'admin', N'KH4', N'KM004', N'25 Oct 2025', 1000, 0),
(N'HD005', N'admin', N'KH2', N'KM002', N'01 Nov 2025', 1200, 0),
(N'HD006', N'admin', N'KH3', N'KM001', N'02 Nov 2025', 850, 0);

-- ======================= BẢNG tblkhachhang =======================
CREATE TABLE tblkhachhang (
  MaKH nvarchar(20) NOT NULL,
  Ho nvarchar(20) NOT NULL,
  Ten nvarchar(20) NOT NULL,
  GioiTinh nvarchar(20) NOT NULL,
  DiaChi nvarchar(40) NOT NULL,
  Email nvarchar(40) NOT NULL,
  Loai nvarchar(20) NOT NULL,
  TongChiTieu float NOT NULL
);

INSERT INTO tblkhachhang (MaKH, Ho, Ten, GioiTinh, DiaChi, Email, Loai, TongChiTieu) VALUES
(N'KH1', N'Phùng Tấn', N'Nhựt', N'Nam', N'123321123321 địa chỉ', N'nguyenanh@gmail.com', N'vip', 58939.3),
(N'KH2', N'Huỳnh Anh', N'Tuấn', N'Nam', N'12332 địa chỉ', N'tuan@gmail.com', N'đặc biệt', 0),
(N'KH3', N'Trần Gia', N'Phú', N'Nam', N'13321 địa chỉ', N'phu@gmail.com', N'vip', 98495),
(N'KH4', N'Nguyễn Ngọc Hạnh', N'Nguyên', N'Nữ', N'1231 địa chỉ', N'nguyen@gmail.com', N'bình thường', 53576),
(N'KH5', N'Lê Văn', N'Hậu', N'Nam', N'123 Đường Mới, TP HCM', N'levanhau@gmail.com', N'bình thường', 3500),
(N'KH6', N'Nguyễn Thị', N'Thơ', N'Nữ', N'456 Đường Hoa, Đà Nẵng', N'thongoan@gmail.com', N'vip', 7800);

-- ======================= BẢNG tblkhuyenmai =======================
CREATE TABLE tblkhuyenmai (
  MaKM nvarchar(20) NOT NULL,
  TenChuongTrinh nvarchar(40) NOT NULL,
  LoaiChuongTrinh nvarchar(20) NOT NULL,
  DieuKien nvarchar(20) NOT NULL,
  NgayBatDau nvarchar(20) NOT NULL,
  NgayKetThuc nvarchar(20) NOT NULL,
  deleted TINYINT DEFAULT 0
);

INSERT INTO tblkhuyenmai (MaKM, TenChuongTrinh, LoaiChuongTrinh, DieuKien, NgayBatDau, NgayKetThuc, deleted) VALUES
(N'KM001', N'Mùa xuân', N'Loại 1', N'vip', N'13 Sep 2025', N'15 Sep 2025', 0),
(N'KM002', N'Mùa hạ', N'Loại 1', N'vip', N'13 Sep 2025', N'15 Sep 2025', 0),
(N'KM003', N'Ngày thu', N'Loại 2', N'đặc biệt', N'17 Sep 2025', N'19 Sep 2025', 0),
(N'KM004', N'Mùa đông', N'Loại 3', N'bình thường', N'21 Sep 2025', N'23 Sep 2025', 0),
(N'KM005', N'BlackFriday', N'Loại 3', N'bình thường', N'21 Sep 2025', N'23 Sep 2025', 0);

-- ======================= BẢNG tblloai =======================
CREATE TABLE tblloai (
  Maloai nvarchar(20) NOT NULL,
  Tenloai nvarchar(20) NOT NULL
);
INSERT INTO tblloai VALUES
(N'1', N'Sneaker'),
(N'2', N'Fashion'),
(N'3', N'Basketball'),
(N'4', N'Soccer');

-- ======================= BẢNG tblmausac =======================
CREATE TABLE tblmausac (
  Mamau nvarchar(20) NOT NULL,
  Tenmau nvarchar(20) NOT NULL
);
INSERT INTO tblmausac VALUES
(N'BLK', N'Black'),
(N'BLU', N'Blue'),
(N'GR', N'Green'),
(N'ORG', N'Orange'),
(N'WT', N'White');

-- ======================= BẢNG tblnhacungcap =======================
CREATE TABLE tblnhacungcap (
  MaNCC nvarchar(20) NOT NULL,
  TenNCC nvarchar(20) NOT NULL,
  DiaChi nvarchar(40) NOT NULL,
  Email nvarchar(40) NOT NULL
);
INSERT INTO tblnhacungcap VALUES
(N'NCC1', N'Rồng Thiên', N'123 Ra đường bị la, P ABC, TP HN', N'rongthien619@gmail.com'),
(N'NCC2', N'Trời Đỏ', N'456 kế bên nhà 123', N'sunred916@gmail.com'),
(N'NCC3', N'Rực lửa', N'135 là số lẻ không phải số chẵn', N'246@gmail.com'),
(N'NCC4', N'Bước Nhảy', N'246 không phải là số chẳn mà là số chẵn', N'jumpandjump@gmail.com');

-- ======================= BẢNG tblnhanvien =======================
CREATE TABLE tblnhanvien (
  MaNV nvarchar(20) NOT NULL,
  Ho nvarchar(30) NOT NULL,
  Ten nvarchar(10) NOT NULL,
  GioiTinh nvarchar(10) NOT NULL,
  DiaChi nvarchar(50) NOT NULL,
  DienThoai int NOT NULL,
  Email nvarchar(40) NOT NULL,
  Luong int NOT NULL,
  ChucVu nvarchar(20) NOT NULL,
  Anh nvarchar(10) NOT NULL
);
INSERT INTO tblnhanvien VALUES
(N'admin', N'Quản trị', N'Viên', N'Nam', N'123 Đi la cà,P đi,Q Ăn, TPHCM', 1001011101, N'admin@admin.vn', 999999, N'Quản trị viên', N'boy 1'),
(N'id1', N'Phùng Tấn', N'Nhựt', N'Nam', N'123 Nguyện Văn A,P.A, Q.A,TPHCM', 1234567890, N'nguyenvanan@gamil.co', 1000, N'Thư ký', N'boy 2'),
(N'id2', N'Huỳnh Anh', N'Tuấn', N'Nam', N'123 Lạc Văn Biển,P.A, Q.A,TPHCM', 1239876540, N'taunhobien@gamil.com', 800, N'Kiểm tra hàng hóa', N'boy 3'),
(N'id3', N'Trần Gia', N'Phú', N'Nam', N'123 A,P LA,Q DHA,TpHCM', 2147483647, N'trandan@gmail.com', 300, N'Nhập Hàng', N'boy 4'),
(N'id4', N'Nguyễn Ngọc Hạnh', N'Nguyên', N'Nữ', N'3 ABCD,P LA,Q DHA,TpHCM', 41234111, N'levanhoang@gmail.com', 8000, N'Khuyến Mãi', N'girl 5'),
(N'id5', N'Nguyễn Văn', N'Kiệt', N'Nam', N'13 An Mà,P LA,Q DHA,TpHCM', 123456789, N'nguyenthilekim@gmail', 900, N'Hóa Đơn', N'boy 6');

-- ======================= BẢNG tblphieunhap =======================
CREATE TABLE tblphieunhap (
  MaPN nvarchar(20) NOT NULL,
  MaNCC nvarchar(20) NOT NULL,
  MaNV nvarchar(20) NOT NULL,
  NgayNhap nvarchar(20) NOT NULL,
  TongTien float NOT NULL,
  deleted TINYINT DEFAULT 0
);

INSERT INTO tblphieunhap VALUES
(N'PN001', N'NCC1', N'admin', N'25 Oct 2025', 10500, 0),
(N'PN002', N'NCC2', N'admin', N'25 Oct 2025', 3000, 0),
(N'PN003', N'NCC3', N'admin', N'25 Oct 2025', 2100, 0),
(N'PN004', N'NCC4', N'admin', N'01 Nov 2025', 7000, 0);

-- ======================= BẢNG tblsanpham =======================
CREATE TABLE tblsanpham (
  Magiay nvarchar(20) NOT NULL,
  Tengiay nvarchar(20) NOT NULL,
  Soluong int NOT NULL,
  Gia int NOT NULL,
  Size int NOT NULL,
  Doituongsd nvarchar(20) NOT NULL,
  Chatlieu nvarchar(20) NOT NULL,
  Maloai nvarchar(20) NOT NULL,
  Maxx nvarchar(20) NOT NULL,
  Mamau nvarchar(20) NOT NULL,
  Mathuonghieu nvarchar(20) NOT NULL,
  deleted TINYINT DEFAULT 0
);

INSERT INTO tblsanpham VALUES
(N'SP001', N'SuperStar', 1, 150, 29, N'Nam', N'Vải', N'3', N'US', N'WT', N'AD', 0),
(N'SP002', N'StanSmith', 2, 200, 40, N'Nam', N'Vải', N'2', N'VN', N'BLK', N'TO', 0),
(N'SP003', N'MLB', 3, 250, 40, N'Tất cả', N'NanoGen', N'3', N'US', N'WT', N'BT', 0),
(N'SP004', N'Yezzy', 6, 350, 34, N'Nữ', N'Vải', N'3', N'ID', N'GR', N'NK', 0),
(N'SP005', N'Converse', 6, 350, 34, N'Nữ', N'Vải', N'3', N'ID', N'GR', N'NK', 0),
(N'SP006', N'CloudFoam', 6, 350, 34, N'Nữ', N'Vải', N'3', N'ID', N'GR', N'NK', 0),
(N'SP007', N'Jordan Retro', 8, 500, 42, N'Nam', N'Da', N'3', N'US', N'BLK', N'NK', 0),
(N'SP008', N'UltraBoost', 10, 700, 40, N'Nam', N'Vải lưới', N'1', N'UK', N'WT', N'AD', 0),
(N'SP009', N'ChuckTaylor', 12, 400, 39, N'Nữ', N'Canvas', N'2', N'VN', N'ORG', N'BT', 0),
(N'SP010', N'Puma DriftCat', 5, 600, 41, N'Nam', N'Da', N'4', N'CN', N'BLU', N'PM', 0);

-- ======================= BẢNG tbltaikhoan =======================
CREATE TABLE tbltaikhoan (
  tendangnhap nvarchar(20) NOT NULL,
  matkhau nvarchar(20) NOT NULL,
  capbac int NOT NULL
);
INSERT INTO tbltaikhoan VALUES
(N'admin', N'admin', 1),
(N'id1', N'123', 2),
(N'id2', N'123', 3),
(N'id3', N'123', 4),
(N'id4', N'123', 5),
(N'id5', N'123', 6);

-- ======================= BẢNG tblthuonghieu =======================
CREATE TABLE tblthuonghieu (
  Mathuonghieu nvarchar(20) NOT NULL,
  Tenthuonghieu nvarchar(30) NOT NULL,
  Diachi nvarchar(40) NOT NULL,
  Email nvarchar(30) NOT NULL
);
INSERT INTO tblthuonghieu VALUES
(N'PM', N'Puma', N'England', N'@@@gmial.com'),
(N'AD', N'Adidas', N'USA', N'adidas@gmail.com'),
(N'BT', N'Bitis', N'VietNam', N'bitis@gmial.com'),
(N'NK', N'Nike', N'USA', N'nike@gmail.com'),
(N'TO', N'ToOng', N'VietNam', N'toong@gmial.com');

-- ======================= BẢNG tblxuatxu =======================
CREATE TABLE tblxuatxu (
  Maxx nvarchar(20) NOT NULL,
  Tennuoc nvarchar(20) NOT NULL
);
INSERT INTO tblxuatxu (Maxx, Tennuoc) VALUES
(N'CN', N'China'),
(N'ID', N'Indonesia'),
(N'UK', N'United Kingdom'),
(N'US', N'USA'),
(N'VN', N'VietNam');

-- Chỉ mục và khóa chính
-- tblchitiethd
ALTER TABLE tblchitiethd
  ADD CONSTRAINT PK_tblchitiethd PRIMARY KEY (Magiay, MaHD);

CREATE INDEX IX_tblchitiethd_MaHD ON tblchitiethd(MaHD);
CREATE INDEX IX_tblchitiethd_Magiay ON tblchitiethd(Magiay);

-- tblchitietkm
ALTER TABLE tblchitietkm
  ADD CONSTRAINT PK_tblchitietkm PRIMARY KEY (MaGiay, MaKM);

CREATE INDEX IX_tblchitietkm_MaGiay ON tblchitietkm(MaGiay);
CREATE INDEX IX_tblchitietkm_MaKM ON tblchitietkm(MaKM);

-- tblchitietpn
ALTER TABLE tblchitietpn
  ADD CONSTRAINT PK_tblchitietpn PRIMARY KEY (MaPN, MaGiay);

CREATE INDEX IX_tblchitietpn_MaPN ON tblchitietpn(MaPN);
CREATE INDEX IX_tblchitietpn_MaGiay ON tblchitietpn(MaGiay);

-- tblhoadon
ALTER TABLE tblhoadon
  ADD CONSTRAINT PK_tblhoadon PRIMARY KEY (MaHD);

CREATE INDEX IX_tblhoadon_MaNV ON tblhoadon(MaNV);
CREATE INDEX IX_tblhoadon_MaKM ON tblhoadon(MaKM);
CREATE INDEX IX_tblhoadon_MaKH ON tblhoadon(MaKH);

-- tblkhachhang
ALTER TABLE tblkhachhang
  ADD CONSTRAINT PK_tblkhachhang PRIMARY KEY (MaKH);

-- tblkhuyenmai
ALTER TABLE tblkhuyenmai
  ADD CONSTRAINT PK_tblkhuyenmai PRIMARY KEY (MaKM);

-- tblloai
ALTER TABLE tblloai
  ADD CONSTRAINT PK_tblloai PRIMARY KEY (Maloai);

-- tblmausac
ALTER TABLE tblmausac
  ADD CONSTRAINT PK_tblmausac PRIMARY KEY (Mamau);

-- tblnhacungcap
ALTER TABLE tblnhacungcap
  ADD CONSTRAINT PK_tblnhacungcap PRIMARY KEY (MaNCC);

-- tblnhanvien
ALTER TABLE tblnhanvien
  ADD CONSTRAINT PK_tblnhanvien PRIMARY KEY (MaNV);

-- tblphieunhap
ALTER TABLE tblphieunhap
  ADD CONSTRAINT PK_tblphieunhap PRIMARY KEY (MaPN);

CREATE INDEX IX_tblphieunhap_MaNCC ON tblphieunhap(MaNCC);
CREATE INDEX IX_tblphieunhap_MaNV ON tblphieunhap(MaNV);

-- tblsanpham
ALTER TABLE tblsanpham
  ADD CONSTRAINT PK_tblsanpham PRIMARY KEY (Magiay);

CREATE INDEX IX_tblsanpham_Maxx ON tblsanpham(Maxx);
CREATE INDEX IX_tblsanpham_Mathuonghieu ON tblsanpham(Mathuonghieu);
CREATE INDEX IX_tblsanpham_Mamau ON tblsanpham(Mamau);
CREATE INDEX IX_tblsanpham_Maloai ON tblsanpham(Maloai);

-- tbltaikhoan
ALTER TABLE tbltaikhoan
  ADD CONSTRAINT PK_tbltaikhoan PRIMARY KEY (tendangnhap);

-- tblthuonghieu
ALTER TABLE tblthuonghieu
  ADD CONSTRAINT PK_tblthuonghieu PRIMARY KEY (Mathuonghieu);

-- tblxuatxu
ALTER TABLE tblxuatxu
  ADD CONSTRAINT PK_tblxuatxu PRIMARY KEY (Maxx);

-- Khóa ngoại
-- tblchitiethd
ALTER TABLE tblchitiethd
  ADD CONSTRAINT FK_tblchitiethd_MaHD FOREIGN KEY (MaHD) REFERENCES tblhoadon(MaHD),
      CONSTRAINT FK_tblchitiethd_Magiay FOREIGN KEY (Magiay) REFERENCES tblsanpham(Magiay);

-- tblchitietkm
ALTER TABLE tblchitietkm
  ADD CONSTRAINT FK_tblchitietkm_MaGiay FOREIGN KEY (MaGiay) REFERENCES tblsanpham(Magiay),
      CONSTRAINT FK_tblchitietkm_MaKM FOREIGN KEY (MaKM) REFERENCES tblkhuyenmai(MaKM);

-- tblchitietpn
ALTER TABLE tblchitietpn
  ADD CONSTRAINT FK_tblchitietpn_MaGiay FOREIGN KEY (MaGiay) REFERENCES tblsanpham(Magiay),
      CONSTRAINT FK_tblchitietpn_MaPN FOREIGN KEY (MaPN) REFERENCES tblphieunhap(MaPN);

-- tblhoadon
ALTER TABLE tblhoadon
  ADD CONSTRAINT FK_tblhoadon_MaKH FOREIGN KEY (MaKH) REFERENCES tblkhachhang(MaKH),
      CONSTRAINT FK_tblhoadon_MaKM FOREIGN KEY (MaKM) REFERENCES tblkhuyenmai(MaKM),
      CONSTRAINT FK_tblhoadon_MaNV FOREIGN KEY (MaNV) REFERENCES tblnhanvien(MaNV);

-- tblphieunhap
ALTER TABLE tblphieunhap
  ADD CONSTRAINT FK_tblphieunhap_MaNCC FOREIGN KEY (MaNCC) REFERENCES tblnhacungcap(MaNCC),
      CONSTRAINT FK_tblphieunhap_MaNV FOREIGN KEY (MaNV) REFERENCES tblnhanvien(MaNV);

-- tblsanpham
ALTER TABLE tblsanpham
  ADD CONSTRAINT FK_tblsanpham_Maloai FOREIGN KEY (Maloai) REFERENCES tblloai(Maloai),
      CONSTRAINT FK_tblsanpham_Mamau FOREIGN KEY (Mamau) REFERENCES tblmausac(Mamau),
      CONSTRAINT FK_tblsanpham_Mathuonghieu FOREIGN KEY (Mathuonghieu) REFERENCES tblthuonghieu(Mathuonghieu),
      CONSTRAINT FK_tblsanpham_Maxx FOREIGN KEY (Maxx) REFERENCES tblxuatxu(Maxx);

-- tbltaikhoan
ALTER TABLE tbltaikhoan
  ADD CONSTRAINT FK_tbltvaikhoan_MaNV FOREIGN KEY (tendangnhap) REFERENCES tblnhanvien(MaNV);