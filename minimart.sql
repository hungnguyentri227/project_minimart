USE [Minimart]
GO
/****** Object:  Table [dbo].[tblallorder]    Script Date: 12/6/2019 10:55:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblallorder](
	[idcustomer] [nvarchar](50) NULL,
	[Date] [date] NULL,
	[total] [int] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblcategory]    Script Date: 12/6/2019 10:55:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblcategory](
	[ID] [int] NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[isdelete1] [bit] NULL,
 CONSTRAINT [PK_tblcategory_1] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblcustomer]    Script Date: 12/6/2019 10:55:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblcustomer](
	[id] [nvarchar](50) NOT NULL,
	[firstname] [nvarchar](50) NULL,
	[lastname] [nvarchar](50) NULL,
	[tel] [nvarchar](50) NOT NULL,
	[email] [nvarchar](50) NULL,
	[isdelete] [bit] NULL,
 CONSTRAINT [PK_tblcustomer_1] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblorder]    Script Date: 12/6/2019 10:55:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblorder](
	[name] [nvarchar](50) NULL,
	[price] [int] NULL,
	[quantity] [int] NULL,
	[tong] [int] NULL,
	[orderID] [nvarchar](50) NULL,
	[idcustomer] [nvarchar](50) NULL,
	[namestaff] [nvarchar](50) NULL,
	[date] [nvarchar](50) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblproduct]    Script Date: 12/6/2019 10:55:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblproduct](
	[ID] [int] NOT NULL,
	[nameProduct] [nvarchar](50) NULL,
	[Category] [nvarchar](50) NULL,
	[price] [int] NULL,
	[description] [nvarchar](50) NULL,
	[isdelete] [bit] NULL,
	[quantity] [int] NULL,
 CONSTRAINT [PK_tblproduct] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbluser]    Script Date: 12/6/2019 10:55:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbluser](
	[id] [nvarchar](50) NOT NULL,
	[username] [nvarchar](50) NULL,
	[password] [nvarchar](50) NULL,
	[fullname] [nvarchar](50) NULL,
	[tel] [nvarchar](50) NULL,
	[email] [nvarchar](50) NULL,
	[role] [nvarchar](50) NULL,
	[isdelete] [bit] NULL,
 CONSTRAINT [PK_tbluser] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[tblcategory] ([ID], [name], [isdelete1]) VALUES (1, N'do uong', 1)
INSERT [dbo].[tblcategory] ([ID], [name], [isdelete1]) VALUES (2, N'do an', 1)
INSERT [dbo].[tblcategory] ([ID], [name], [isdelete1]) VALUES (3, N'do dung', 1)
INSERT [dbo].[tblcustomer] ([id], [firstname], [lastname], [tel], [email], [isdelete]) VALUES (N'CUS-01', N'nguyen tri', N'hung', N'1234567890', N'hung@gmail.co', 1)
INSERT [dbo].[tblcustomer] ([id], [firstname], [lastname], [tel], [email], [isdelete]) VALUES (N'CUS-02', N'nguyen huy', N'hoang', N'1234567891', N'hoang@gmail.co', 1)
INSERT [dbo].[tblcustomer] ([id], [firstname], [lastname], [tel], [email], [isdelete]) VALUES (N'CUS-03', N'hoang thi', N'ha1', N'1234567890', N'ha@gmail.vn', 1)
INSERT [dbo].[tblorder] ([name], [price], [quantity], [tong], [orderID], [idcustomer], [namestaff], [date]) VALUES (N'Cocacola', 50, 2, 100, N'DA_18', N'CUS-01', N'tutai', N'06/12/2019')
INSERT [dbo].[tblorder] ([name], [price], [quantity], [tong], [orderID], [idcustomer], [namestaff], [date]) VALUES (N'pepsi', 100, 1, 100, N'DA_18', N'CUS-01', N'tutai', N'06/12/2019')
INSERT [dbo].[tblorder] ([name], [price], [quantity], [tong], [orderID], [idcustomer], [namestaff], [date]) VALUES (N'7up', 100, 1, 100, N'DA_18', N'CUS-01', N'tutai', N'06/12/2019')
INSERT [dbo].[tblorder] ([name], [price], [quantity], [tong], [orderID], [idcustomer], [namestaff], [date]) VALUES (N'5', 5, 3, 15, N'DA_22', N'CUS-01', N'tutai', N'06/12/2019')
INSERT [dbo].[tblorder] ([name], [price], [quantity], [tong], [orderID], [idcustomer], [namestaff], [date]) VALUES (N'pepsi', 100, 3, 300, N'DA_22', N'CUS-01', N'tutai', N'06/12/2019')
INSERT [dbo].[tblorder] ([name], [price], [quantity], [tong], [orderID], [idcustomer], [namestaff], [date]) VALUES (N'5', 5, 4, 20, N'DA_22', N'CUS-01', N'tutai', N'06/12/2019')
INSERT [dbo].[tblorder] ([name], [price], [quantity], [tong], [orderID], [idcustomer], [namestaff], [date]) VALUES (N'pepsi', 100, 3, 300, N'DA_22', N'CUS-01', N'tutai', N'06/12/2019')
INSERT [dbo].[tblproduct] ([ID], [nameProduct], [Category], [price], [description], [isdelete], [quantity]) VALUES (1, N'Cocacola', N'do uong', 50, N'nuoc uong co ga', 1, 48)
INSERT [dbo].[tblproduct] ([ID], [nameProduct], [Category], [price], [description], [isdelete], [quantity]) VALUES (2, N'sushi', N'do an', 50, N'do an nhat ban', 1, 50)
INSERT [dbo].[tblproduct] ([ID], [nameProduct], [Category], [price], [description], [isdelete], [quantity]) VALUES (3, N'pepsi', N'do uong', 100, N'do uong co ga', 1, 43)
INSERT [dbo].[tblproduct] ([ID], [nameProduct], [Category], [price], [description], [isdelete], [quantity]) VALUES (4, N'7up', N'do uong', 100, N'do uong co ga', 1, 49)
INSERT [dbo].[tblproduct] ([ID], [nameProduct], [Category], [price], [description], [isdelete], [quantity]) VALUES (5, N'5', N'do uong', 5, N'5', 1, 196)
INSERT [dbo].[tbluser] ([id], [username], [password], [fullname], [tel], [email], [role], [isdelete]) VALUES (N'1', N'hung', N'1', N'trihung', N'1234567890', N'hung@gmail.co', N'admin', 1)
INSERT [dbo].[tbluser] ([id], [username], [password], [fullname], [tel], [email], [role], [isdelete]) VALUES (N'5', N'duyanh', N'1', N'duyanh', N'1234567890', N'duyanh@gmail.co', N'staff', 1)
INSERT [dbo].[tbluser] ([id], [username], [password], [fullname], [tel], [email], [role], [isdelete]) VALUES (N'6', N'tutai', N'2', N'tutai', N'1234567890', N'hung@gmail.co', N'staff', 1)
INSERT [dbo].[tbluser] ([id], [username], [password], [fullname], [tel], [email], [role], [isdelete]) VALUES (N'8', N'hung123', N'123', N'trihung1', N'1234567890', N'hung@gmail.c', N'staff', 0)
