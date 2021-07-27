use master
GO
Drop DATABASE QUANLYDVD
GO
create database QUANLYDVD
GO
USE QUANLYDVD
GO
CREATE TABLE Customers(
	idCustomer varchar(10) primary key,
	fullName ntext not null,
	address ntext not null,
	phoneNumber nvarchar(11) not null unique
)
GO
CREATE TABLE ProductTypes(
	idType varchar(10) primary key,
	nameType nvarchar(30) not null,
	price BIGINT, 
	lateCharge BIGINT
)
GO
CREATE TABLE Titles (
	idTitle varchar(10) primary key,
	nameTitle nvarchar(50) not null unique,
	typeTitle  nvarchar(20),
	idType varchar(10),
	CONSTRAINT idType FOREIGN KEY (idType) REFERENCES ProductTypes (idType) ON DELETE CASCADE ON UPDATE CASCADE
)
GO
CREATE TABLE Products(
	idProduct varchar(10) primary key,
	status nvarchar (30),
	idTitle varchar(10),
	CONSTRAINT idTitle FOREIGN KEY (idTitle) REFERENCES Titles (idTitle) ON DELETE CASCADE ON UPDATE CASCADE
)
GO
CREATE TABLE RentSlips(
	idRent varchar(10) primary key,
	rentDate date,
	idCustomer varchar(10),
	CONSTRAINT idCustomer FOREIGN KEY (idCustomer) REFERENCES Customers (idCustomer) ON DELETE CASCADE ON UPDATE CASCADE
)
GO
CREATE TABLE RentSlipDetails(
	idRent varchar(10) not null,
	idProduct varchar(10) not null,
	returnDate date not null,
	price BIGINT 
)
GO
CREATE TABLE ReturnSlips(
	idReturn varchar(10) primary key,
	returnTime date not null,
	idCustomerRV varchar(10),
	CONSTRAINT idCustomerRV FOREIGN KEY (idCustomerRV) REFERENCES Customers (idCustomer) ON DELETE CASCADE ON UPDATE CASCADE
)
GO
CREATE TABLE ReturnSlipDetails(
	idReturn varchar(10) not null,
	idProduct varchar(10) not null,
	lateCharge BIGINT
)
GO 
CREATE TABLE Accounts(
	id varchar(20) not null primary key,
	password varchar(20) not null,
)

GO
CREATE TABLE Reservations(
	idReservation varchar(10) not null primary key,
	amount BIGINT,
	status bit,
	idCustomerR varchar(10),
	idProduct varchar(10),
	CONSTRAINT idCustomerR FOREIGN KEY (idCustomerR) REFERENCES Customers (idCustomer) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT idProduct FOREIGN KEY (idProduct) REFERENCES Products (idProduct) ON DELETE CASCADE ON UPDATE CASCADE,
)

GO

--Tạo khóa chính cho các bảng chi tiết 
GO
ALTER TABLE RentSlipDetails ADD CONSTRAINT PK_idProduct_idRent primary key (idProduct,idRent)
ALTER TABLE ReturnSlipDetails ADD CONSTRAINT PK_idProduct_idReturnVouncher primary key (idProduct,idReturn)

--Tạo khóa ngoại cho các bảng chi tiết
GO
ALTER TABLE RentSlipDetails ADD CONSTRAINT FK_RentSlipDetails_idProduct FOREIGN KEY (idProduct) REFERENCES Products (idProduct)
GO
ALTER TABLE RentSlipDetails ADD CONSTRAINT FK_RentSlipDetails_idRent FOREIGN KEY (idRent) REFERENCES RentSlips (idRent)
GO
ALTER TABLE ReturnSlipDetails ADD CONSTRAINT FK_ReturnSlipDetails_idProduct FOREIGN KEY (idProduct) REFERENCES Products (idProduct)
GO
ALTER TABLE ReturnSlipDetails ADD CONSTRAINT FK_ReturnSlipDetails_idReturn FOREIGN KEY (idReturn) REFERENCES ReturnSlips (idReturn)
GO

--Tạo hàm tăng tự động mã
--@lastID: mã cuối cùng
--@prefix: tiền tố mã KH
--@size độ dài mã
create function func_NextID(@lastID varchar(10), @prefix varchar(3), @size int)
returns varchar(10)
AS 
	BEGIN
		IF(@lastID = '')
			SET @lastID = @prefix + REPLICATE(0, @size - LEN(@prefix))
		DECLARE @num_nextID int, @nextID varchar(10)
		SET @lastID = LTRIM(RTRIM(@lastID))
		SET @num_nextID = REPLACE(@lastID,@prefix,'') + 1
		SET @size = @size - LEN(@prefix)
		SET @nextID = @prefix + REPLICATE(0, @size - LEN(@prefix))
		SET @nextID = @prefix + RIGHT(REPLICATE(0, @size) + CONVERT (VARCHAR(MAX), @num_nextID), @size)
		return @nextID
	END 
GO

--Tạo trigger cập nhật mã 
create trigger nextCustomerID on Customers
FOR INSERT 
AS 
	BEGIN
		DECLARE @lastID VARCHAR(10) 
		SET @lastID = (SELECT TOP 1 idCustomer FROM Customers order by idCustomer desc)
		UPDATE Customers set idCustomer = dbo.func_NextID(@lastID, 'KH', 10) WHERE  idCustomer = ''
	END
GO
create trigger nextTitleID on Titles
FOR INSERT 
AS 
	BEGIN
		DECLARE @lastID VARCHAR(10) 
		SET @lastID = (SELECT TOP 1 idTitle FROM Titles order by idTitle desc)
		UPDATE Titles set idTitle = dbo.func_NextID(@lastID, 'TT', 10) WHERE  idTitle = ''
	END
GO
create trigger nextRentalID on RentSlips
FOR INSERT 
AS 
	BEGIN
		DECLARE @lastID VARCHAR(10) 
		SET @lastID = (SELECT TOP 1 idRent FROM RentSlips order by idRent desc)
		UPDATE RentSlips set idRent = dbo.func_NextID(@lastID, 'RT', 10) WHERE  idRent = ''
	END
GO
create trigger nextReturnID on ReturnSlips
FOR INSERT 
AS 
	BEGIN
		DECLARE @lastID VARCHAR(10) 
		SET @lastID = (SELECT TOP 1 idReturn FROM ReturnSlips order by idReturn desc)
		UPDATE ReturnSlips set idReturn = dbo.func_NextID(@lastID, 'RTN', 10) WHERE  idReturn = ''
	END
GO
create trigger nextReservatioID on Reservations
FOR INSERT 
AS 
	BEGIN
		DECLARE @lastID VARCHAR(10) 
		SET @lastID = (SELECT TOP 1 idReservation FROM Reservations order by idReservation desc)
		UPDATE Reservations set idReservation = dbo.func_NextID(@lastID, 'R', 10) WHERE  idReservation = ''
	END




GO
SET DATEFORMAT dmy;

--Tạo các giá trị (records) cho các bảng
--insert records table Accounts
GO
insert into [dbo].[Accounts] values('quanly','123456')
--insert records table Customers
GO
insert into [dbo].[Customers] values ('', N'Nguyễn Thanh Tường', N'67/37 100 Bình Thới', '0908444271')
insert into [dbo].[Customers] values ('', N'Nguyễn Hữu Cương', N'107 Tân Phước', '0908161202')
insert into [dbo].[Customers] values ('', N'Nguyễn Đức Trung', N'Cao Ốc B Nguyễn Kim', '0908123561')
insert into [dbo].[Customers] values ('', N'Lê Thanh Nghĩa', N'159/271A Phó Cơ Điều', '0908123455')
insert into [dbo].[Customers] values ('', N'Trần Tiến Việt', N'172/27 Tạ Uyên', '0907564895')
insert into [dbo].[Customers] values ('', N'Trọng Đức Hiếu', N'134 Lò Siêu', '0907364565')
insert into [dbo].[Customers] values ('', N'Trần Trung Hiếu', N'591/6/6 Bình Thới', '0904563189')
insert into [dbo].[Customers] values ('', N'Trần Thiên Ân', N'48/9Q Tân Hóa', '0902159645')
insert into [dbo].[Customers] values ('', N'Võ Nguyễn Hữu Vi', N'9/8 Quang Trung', '0908564623')
insert into [dbo].[Customers] values ('', N'Trần Đức Vũ Long', N'Đường số 2 Hiệp Bình Phước', '0903156495')
insert into [dbo].[Customers] values ('', N'Nguyễn Anh Tiến', N'67/37 100 Bình Thới', '0907156121')
insert into [dbo].[Customers] values ('', N'Tô Uyển Nhi', N'67/37 100 Bình Thới', '0908134156')
insert into [dbo].[Customers] values ('', N'Vũ Văn Long', N'67/37 100 Bình Thới', '0902164894')
insert into [dbo].[Customers] values ('', N'Trần Đức Hữu Cảnh', N'67/37 100 Bình Thới', '0901654892')
insert into [dbo].[Customers] values ('', N'Huỳnh Ngọc Tiên', N'67/37 100 Bình Thới', '0908143246')
insert into [dbo].[Customers] values ('', N'Huỳnh Vĩnh Thái', N'67/37 100 Bình Thới', '0905647895')
insert into [dbo].[Customers] values ('', N'Huỳnh Kim Lợi', N'67/37 100 Bình Thới', '0905685464')
insert into [dbo].[Customers] values ('', N'Võ Hoàng Vinh', N'67/37 100 Bình Thới', '0908165472')
insert into [dbo].[Customers] values ('', N'Trần Kim Long', N'67/37 100 Bình Thới', '0908555627')
insert into [dbo].[Customers] values ('', N'Võ Văn Hậu', N'67/37 100 Bình Thới', '0908213485')

--insert records table ProductTypes
GO
insert into [dbo].[ProductTypes] values ('DVD001',N'DVD', 3000,1000)
insert into [dbo].[ProductTypes] values ('Disk001',N'Disk', 2000,1000)

--insert records table Titles
GO
insert into [dbo].[Titles] values ('', N'Fast And Furious 2', N'Phim', 'DVD001')
insert into [dbo].[Titles] values ('', N'Fast And Furious 3', N'Phim', 'DVD001')
insert into [dbo].[Titles] values ('', N'Fast And Furious 4', N'Phim', 'DVD001')
insert into [dbo].[Titles] values ('', N'Fast And Furious 5', N'Phim', 'DVD001')
insert into [dbo].[Titles] values ('', N'Fast And Furious 6', N'Phim', 'DVD001')
insert into [dbo].[Titles] values ('', N'Fast And Furious 7', N'Phim', 'DVD001')
insert into [dbo].[Titles] values ('', N'Fast And Furious 8', N'Phim', 'DVD001')
insert into [dbo].[Titles] values ('', N'Fast And Furious 9', N'Phim', 'DVD001')
insert into [dbo].[Titles] values ('', N'Super Mario', N'Trò chơi', 'Disk001')
insert into [dbo].[Titles] values ('', N'Among Us', N'Trò chơi', 'Disk001')
insert into [dbo].[Titles] values ('', N'Halo: The Master Chief Collection', N'Trò chơi', 'Disk001')
insert into [dbo].[Titles] values ('', N'PLAYERUNKNOWNS BATTLEGROUNDS', N'Trò chơi', 'Disk001')
insert into [dbo].[Titles] values ('', N'Dark Soul III', N'Trò chơi', 'Disk001')
insert into [dbo].[Titles] values ('', N'DEATH STRANDING', N'Trò chơi', 'Disk001')


--insert records table Products
GO
insert into [dbo].[Products] values ('P010203',N'Trên giá',N'TT00000001')
insert into [dbo].[Products] values ('P010204',N'Trên giá',N'TT00000001')
insert into [dbo].[Products] values ('P010205',N'Trên giá',N'TT00000002')
insert into [dbo].[Products] values ('P010206',N'Trên giá',N'TT00000002')
insert into [dbo].[Products] values ('P010207',N'Trên giá',N'TT00000003')
insert into [dbo].[Products] values ('P010208',N'Trên giá',N'TT00000003')
insert into [dbo].[Products] values ('P010209',N'Trên giá',N'TT00000009')
insert into [dbo].[Products] values ('P010210',N'Trên giá',N'TT00000011')
insert into [dbo].[Products] values ('P010211',N'Trên giá',N'TT00000012')
insert into [dbo].[Products] values ('P010212',N'Trên giá',N'TT00000014')
insert into [dbo].[Products] values ('P010213',N'Trên giá',N'TT00000014')

insert into [dbo].[Products] values ('P010214',N'Trên giá',N'TT00000004')
insert into [dbo].[Products] values ('P010215',N'Trên giá',N'TT00000005')
insert into [dbo].[Products] values ('P010216',N'Trên giá',N'TT00000006')
insert into [dbo].[Products] values ('P010217',N'Trên giá',N'TT00000007')
insert into [dbo].[Products] values ('P010218',N'Trên giá',N'TT00000008')
insert into [dbo].[Products] values ('P010219',N'Trên giá',N'TT00000009')
insert into [dbo].[Products] values ('P010220',N'Trên giá',N'TT00000009')
insert into [dbo].[Products] values ('P010221',N'Trên giá',N'TT00000010')
insert into [dbo].[Products] values ('P010222',N'Trên giá',N'TT00000010')
insert into [dbo].[Products] values ('P010223',N'Trên giá',N'TT00000014')
insert into [dbo].[Products] values ('P010224',N'Trên giá',N'TT00000014')

insert into [dbo].[Products] values ('P010225',N'Trên giá',N'TT00000007')
insert into [dbo].[Products] values ('P010226',N'Trên giá',N'TT00000008')
insert into [dbo].[Products] values ('P010227',N'Trên giá',N'TT00000009')
insert into [dbo].[Products] values ('P010228',N'Trên giá',N'TT00000010')
insert into [dbo].[Products] values ('P010229',N'Trên giá',N'TT00000011')
insert into [dbo].[Products] values ('P010230',N'Trên giá',N'TT00000012')
insert into [dbo].[Products] values ('P010231',N'Trên giá',N'TT00000013')
insert into [dbo].[Products] values ('P010232',N'Trên giá',N'TT00000011')
insert into [dbo].[Products] values ('P010233',N'Trên giá',N'TT00000012')
insert into [dbo].[Products] values ('P010234',N'Trên giá',N'TT00000014')
insert into [dbo].[Products] values ('P010235',N'Trên giá',N'TT00000014')

--insert records table Products
GO
insert into [dbo].[RentSlips] values ('', '22/10/2020', 'KH00000001')
insert into [dbo].[RentSlipDetails] values ('RT00000001', 'P010203', '24/11/2020', 3000)
insert into [dbo].[ReturnSlips] values ('', '26/10/2020', 'KH00000001')

insert into [dbo].[Reservations] values ('', 6000, 0, 'KH00000001', 'P010203')
UPDATE [dbo].[Reservations] set status = 0 where idReservation ='R000000001'

select * from RentSlips
select * from RentSlipDetails

SELECT count(idReservation) 
FROM Reservations
WHERE status = 0 AND idCustomerR = 'KH00000001'

SELECT * FRoM Products WHERE idProduct = 'P010203'

select*from [dbo].[ReturnSlips] 
select*from  [dbo].[Reservations]

select*from [dbo].[RentSlips]
SELECT TOP 1 idRent asLastID FROM RentSlips ORDER BY idRent DESC

select*from [dbo].[RentSlipDetails]
delete [dbo].[RentSlips]
delete [dbo].[RentSlipDetails]



select*from [dbo].[ProductTypes]
select*from [dbo].[Titles]
select*from [dbo].[Products]
select*from [dbo].[Customers]
select*from [dbo].[Accounts]


SELECT [price] FROM [dbo].[Products] p join [dbo].[Titles] t on p.idTitle = t.idTitle join [dbo].[ProductTypes] pt on t.idType = pt.idType WHERE [idProduct] = 'P010203'

SELECT price FROM Products p join Titles t on p.idTitle = t.idTitle join ProductTypes pt on t.idType = pt.idType WHERE idProduct = 'P010203'


update [dbo].[Customers] set [fullName] = 'Tam', [address] = 'asd' where [idCustomer] ='C010224' 



delete [dbo].[Customers] where [idCustomer] = '123'

DROP TABLE [dbo].[Customers]
GO
DROP TABLE [dbo].[Titles]

select idProduct, status, nameTitle from [dbo].[Products] inner join [dbo].[Titles] on [dbo].[Products].[idTitle] = [dbo].[Titles].[idTitle]
select idProduct, status, nameTitle from [dbo].[Products] inner join [dbo].[Titles] on [dbo].[Products].[idTitle] = [dbo].[Titles].[idTitle] where nameTitle like 'Fast And Furious 2'





delete Titles where idTitle ='T0102031'
select * from Titles where idTitle = 'T010203'

select count(idTitle) from Titles


drop table [dbo].[Products]



select*from [dbo].[Titles]
select*from [dbo].[ProductTypes]
SELECT * FROM ProductTypes where idType = 'DVD001'

insert into [dbo].[Titles] values ('',N'aaa234', 'Trò chơi','Disk001')


