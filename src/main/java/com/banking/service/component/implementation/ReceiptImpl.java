package com.banking.service.component.implementation;

import com.banking.entity.Transaction;
import com.banking.service.component.interfaces.Receipt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
public class ReceiptImpl implements Receipt {

    @Override
    public String create(List<Transaction> transactions) {
        StringBuilder newTicket = new StringBuilder();
        for (Transaction transaction : transactions) {
            String date = String.valueOf(transaction.getCreatedAt());
            String type = String.valueOf((transaction.getType()));
            String amount = String.valueOf(transaction.getAmount());
            String description = transaction.getDescription();
            String iban = transaction.getIBan();

            String currentTransaction = """
                    Date:           %s
                    Type:           %s
                    Amount:         %s
                    Description:    %s
                    Iban:           %s
                                
                    """.formatted(date, type, amount, description, iban);
            newTicket.append(currentTransaction);
        }
        return String.valueOf(newTicket);
    }

    @Override
    public File saveReport(List<Transaction> transactions) {
        String report = create(transactions);
        File file = new File("report_"+ LocalDateTime.now() + ".txt");
        try (BufferedWriter writer= new BufferedWriter(new FileWriter(file))){
            writer.write(report);
        } catch (IOException e){
            log.error("An error occurred while writing to the file: " + file.getName());
        }
        return file;
    }
}
