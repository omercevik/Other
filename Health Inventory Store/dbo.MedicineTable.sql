CREATE TABLE [dbo].[MedicineTable] (
    [MedicineId]    INT          NOT NULL,
    [MedicineName]  VARCHAR (50) NOT NULL,
    [MedicineUnit]  VARCHAR (20) NOT NULL,
    [MedicineCount] INT          NOT NULL,
    [MedicineDate]  DATETIME     NOT NULL,
    PRIMARY KEY CLUSTERED ([MedicineId] ASC)
);

