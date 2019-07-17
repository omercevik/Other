
-- --------------------------------------------------
-- Entity Designer DDL Script for SQL Server 2005, 2008, 2012 and Azure
-- --------------------------------------------------
-- Date Created: 07/05/2019 14:48:12
-- Generated from EDMX file: C:\Users\s-cevik\Desktop\WebApplication1\WebApplication1\Models\MedicineModel.edmx
-- --------------------------------------------------

SET QUOTED_IDENTIFIER OFF;
GO
USE [SaglikDB];
GO
IF SCHEMA_ID(N'dbo') IS NULL EXECUTE(N'CREATE SCHEMA [dbo]');
GO

-- --------------------------------------------------
-- Dropping existing FOREIGN KEY constraints
-- --------------------------------------------------


-- --------------------------------------------------
-- Dropping existing tables
-- --------------------------------------------------

IF OBJECT_ID(N'[dbo].[MedicineTable]', 'U') IS NOT NULL
    DROP TABLE [dbo].[MedicineTable];
GO

-- --------------------------------------------------
-- Creating all tables
-- --------------------------------------------------

-- Creating table 'MedicineTables'
CREATE TABLE [dbo].[MedicineTables] (
    [MedicineId] int  NOT NULL,
    [MedicineName] varchar(50)  NOT NULL,
    [MedicineDate] varchar(10)  NOT NULL,
    [MedicineUnit] varchar(20)  NOT NULL,
    [MedicineCount] int  NOT NULL,
);
GO

-- --------------------------------------------------
-- Creating all PRIMARY KEY constraints
-- --------------------------------------------------

-- Creating primary key on [MedicineId] in table 'MedicineTables'
ALTER TABLE [dbo].[MedicineTables]
ADD CONSTRAINT [PK_MedicineTables]
    PRIMARY KEY CLUSTERED ([MedicineId] ASC);
GO

-- --------------------------------------------------
-- Creating all FOREIGN KEY constraints
-- --------------------------------------------------

-- --------------------------------------------------
-- Script has ended
-- --------------------------------------------------