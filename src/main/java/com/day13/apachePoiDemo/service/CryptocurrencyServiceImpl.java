package com.day13.apachePoiDemo.service;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.day13.apachePoiDemo.model.Cryptocurrency;
import com.day13.apachePoiDemo.repositiry.CryptocerrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CryptocurrencyServiceImpl implements CryptocurrencyService{

    @Autowired
    private CryptocerrencyRepository cryptocerrencyRepository;


    @Override
    public void importToDb(List<MultipartFile> multipleFiles) {
        if(!multipleFiles.isEmpty()){
            List<Cryptocurrency> cryptocurrencies = new ArrayList<>(6000000000);
            multipleFiles.forEach(multipartFile -> {
                try{
                    XSSFWorkbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
                    XSSFSheet sheet = workbook.getSheetAt(0);

                    //looping----------------------------------------------------------------
                    for (int rowIndex = 1; rowIndex < getNumberOfNonEmptyCells(sheet, 0) - 1; rowIndex++) {

                        XSSFRow row = sheet.getRow(rowIndex);
                        //datatoString skip header

                        Long id = Long.parseLong(getValue(row.getCell(0)).toString());
                        String name = String.valueOf(row.getCell(1));
                        String ticker  = String.valueOf(row.getCell(2));
                        Long coinId = Long.parseLong(getValue(row.getCell(3)).toString());
                        String code = String.valueOf(row.getCell(4));
                        String exchange = String.valueOf(row.getCell(5));
                        short invalid = Short.parseShort(getValue(row.getCell(6)).toString());

                        Integer recordTime = Integer.parseInt(getValue(row.getCell(7)).toString());
                        Long usd = Long.parseLong(getValue(row.getCell(8)).toString());
                        Long idr = Long.parseLong(getValue(row.getCell(9)).toString());
                        Long hnst = Long.parseLong(getValue(row.getCell(10)).toString());
                        Long eth= Long.parseLong(getValue(row.getCell(11)).toString());
                        Long btc = Long.parseLong(getValue(row.getCell(12)).toString());
                        //string formatter date

                        Double create = Double.parseDouble(getValue(row.getCell(13)).toString());
                        Double update = Double.parseDouble(getValue(row.getCell(14)).toString());
                        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

                        LocalDateTime createdAt = LocalDateTime.parse(convertDateEvo(create),dateFormat);
                        LocalDateTime updatedAt = LocalDateTime.parse(convertDateEvo(update),dateFormat);

                        Cryptocurrency cryptocurrency = Cryptocurrency.builder()
                                .id(id)
                                .name(name)
                                .ticker(ticker)
                                .coinId(coinId)
                                .code(code)
                                .exchange(exchange)
                                .invalid(invalid)
                                .recordTime(recordTime)
                                .usd(usd)
                                .idr(idr)
                                .hnst(hnst)
                                .eth(eth)
                                .btc(btc)
                                .createdAt(createdAt)
                                .updatedAt(updatedAt)
                                .build();

                        cryptocurrencies.add(cryptocurrency);

                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            if (!cryptocurrencies.isEmpty()) {
                // save to database
                cryptocerrencyRepository.saveAll(cryptocurrencies);
            }
        }
    }

    public String convertDateEvo (Double date){
        java.util.Date javaDate =org.apache.poi.ss.usermodel.DateUtil.getJavaDate(date);
        return new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(javaDate);
    }

    @Override
    public List<Cryptocurrency> getAllData() {
        return cryptocerrencyRepository.findAll();
    }

    private Object getValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue());
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case ERROR:
                return cell.getErrorCellValue();
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return null;
            case _NONE:
                return null;
            default:
                break;
        }
        return null;
    }

    public static int getNumberOfNonEmptyCells(XSSFSheet sheet, int columnIndex) {
        int numOfNonEmptyCells = 0;
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            XSSFRow row = sheet.getRow(i);
            if (row != null) {
                XSSFCell cell = row.getCell(columnIndex);
                if (cell != null && cell.getCellType() != CellType.BLANK) {
                    numOfNonEmptyCells++;
                }
            }
        }
        return numOfNonEmptyCells;
    }
}
