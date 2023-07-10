package com.banking.dbfiller;

import com.banking.entity.Account;
import com.banking.entity.Agreement;
import com.banking.entity.Client;
import com.banking.entity.Credit;
import com.banking.entity.Document;
import com.banking.entity.Manager;
import com.banking.entity.Product;
import com.banking.entity.Transaction;
import com.banking.entity.entityenumerations.AccountStatus;
import com.banking.entity.entityenumerations.AccountType;
import com.banking.entity.entityenumerations.AgreementStatus;
import com.banking.entity.entityenumerations.ClientStatus;
import com.banking.entity.entityenumerations.CreditStatus;
import com.banking.entity.entityenumerations.CreditType;
import com.banking.entity.entityenumerations.CurrencyCode;
import com.banking.entity.entityenumerations.DeletedStatus;
import com.banking.entity.entityenumerations.ManagerStatus;
import com.banking.entity.entityenumerations.ProductStatus;
import com.banking.entity.entityenumerations.Role;
import com.banking.entity.entityenumerations.TransactionType;
import com.banking.service.component.interfaces.IBanGeneratorComponent;
import com.banking.service.interfaces.AccountService;
import com.banking.service.interfaces.AgreementService;
import com.banking.service.interfaces.ClientService;
import com.banking.service.interfaces.CreditService;
import com.banking.service.interfaces.DocumentService;
import com.banking.service.interfaces.ManagerService;
import com.banking.service.interfaces.ProductService;
import com.banking.service.interfaces.TransactionService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@Component
@Slf4j
public class Filler {

    private final IBanGeneratorComponent ibanGen;
    private final AccountService accountService;
    private final AgreementService agreementService;
    private final ClientService clientService;
    private final CreditService creditService;
    private final DocumentService documentService;
    private final ManagerService managerService;
    private final ProductService productService;
    private final TransactionService transactionService;

    private final FileUtil fileUtil;

    @Value("${filler.firstNames}")
    private String firstNames;

    @Value("${filler.surnames}")
    private String surnames;

    private List<ManagerStatus> managerStatusList = Arrays.asList(ManagerStatus.ACTIVE,
            ManagerStatus.INACTIVE, ManagerStatus.SUSPENDED, ManagerStatus.TERMINATED,
            ManagerStatus.ON_LEAVE, ManagerStatus.PROBATIONARY, ManagerStatus.PROMOTED,
            ManagerStatus.DEMOTED, ManagerStatus.TRANSFERRED, ManagerStatus.RETIRED
    );

    @Value("${filler.taxCodes}")
    private String taxCodes;
    @Value("${filler.phoneNumbers}")
    private String phoneNumbers;
    @Value("${filler.streetNames}")
    private String streetNames;
    @Value("${filler.accountNames}")
    private String accountNames;
    @Value("${filler.bankProductNames}")
    private String bankProductNames;
    @Value("${filler.transactionDescriptions}")
    private String transactionDescriptions;

    private List<AccountType> accountTypes = Arrays.asList(AccountType.values());
    private List<AccountStatus> accountStatusList = Arrays.asList(AccountStatus.values());
    private List<CurrencyCode> currencyCodes = Arrays.asList(CurrencyCode.values());
    private List<AgreementStatus> agreementStatuses = Arrays.asList(AgreementStatus.values());
    private List<DeletedStatus> deletedStatuses = Arrays.asList(DeletedStatus.values());
    private List<ProductStatus> productStatuses = Arrays.asList(ProductStatus.values());
    private List<CreditType> creditTypes = Arrays.asList(CreditType.values());
    private List<CreditStatus> creditStatuses = Arrays.asList(CreditStatus.values());
    private List<TransactionType> transactionTypes = Arrays.asList(TransactionType.values());
    private List<Role> roles = Arrays.asList(Role.values());
    private List<ClientStatus> clientStatuses = Arrays.asList(ClientStatus.values());
    private List<Double> interestRates = List.of(0.5, 0.8, 1.2, 0.3, 0.9, 1.5, 1.0, 0.7, 0.6, 1.3);



    public void fillAll() {
        Random r = new Random();//NOSONAR
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < r.nextInt(1,10); i++) {
            Manager manager = generateManager();
            for (int j = 0; j < r.nextInt(1,10); j++) {
                Client client = generateClient(manager.getId());
                generateDocument(client.getId());//NOSONAR
                Product product = productGenerator(manager.getId());
                for (int k = 0; k < r.nextInt(1,10); k++) {
                    Account account = generateAccount(client.getId());
                    generateCredit(client.getId());//NOSONAR
                    for (int l = 0; l < r.nextInt(1,10); l++) {
                        agreementGenerate(account.getId(), product.getId());//NOSONAR
                        generateTransaction(account.getId());//NOSONAR
                    }
                }
            }
        }
        log.info("time millis = " + (System.currentTimeMillis() - startTime));
    }

    private Manager generateManager() {
        Manager manager = new Manager();
        manager.setFirstName(randomChoice(firstNames));
        manager.setLastName(randomChoice(surnames));
        manager.setStatus(ManagerStatus.ACTIVE);
        manager.setDescription("manager");
        manager.setDeletedStatus(DeletedStatus.ACTIVE);
        manager.setRole(randomChoice(roles));

        return managerService.save(manager);
    }

    private Client generateClient(UUID managerId) {
        Client client = new Client();
        client.setFirstName(randomChoice(firstNames));
        client.setLastName(randomChoice(surnames));
        client.setTaxCode(randomChoice(taxCodes));
        client.setEmail(client.getFirstName() + "_" + client.getLastName() + "@gmail.com");
        client.setPhone(randomChoice(phoneNumbers));
        client.setAddress(randomChoice(streetNames) + new Random().nextInt(100));
        client.setDeletedStatus(DeletedStatus.ACTIVE);
        client.setStatus(ClientStatus.ACTIVE);
        client.setManagerId(managerId);

        return clientService.save(client);
    }

    private Account generateAccount(UUID clientId) {
        Account account = new Account();
        account.setClientId(clientId);
        account.setName(randomChoice(accountNames));
        account.setType(randomChoice(accountTypes));
        account.setStatus(AccountStatus.ACTIVE);
        account.setBalance(BigDecimal.valueOf(new Random().nextDouble()));
        account.setCurrencyCode(randomChoice(currencyCodes));
        account.setDeletedStatus(DeletedStatus.ACTIVE);
        account.setIBan(ibanGen.generate());

        return accountService.save(account);

    }

    private Agreement agreementGenerate(UUID accountId, UUID productId) {
        Agreement agreement = new Agreement();
        agreement.setAccountId(accountId);
        agreement.setProductId(productId);
        agreement.setInterestRate(BigDecimal.valueOf(randomChoice(interestRates)));
        agreement.setStatus(AgreementStatus.ACTIVE);
        agreement.setSum(BigDecimal.valueOf(new Random().nextDouble()));
        agreement.setDeletedStatus(DeletedStatus.ACTIVE);

        return agreementService.save(agreement);

    }

    private Product productGenerator(UUID managerId) {
        Product product = new Product();
        product.setManagerId(managerId);
        product.setName(randomChoice(bankProductNames));
        product.setStatus(ProductStatus.ACTIVE);
        product.setCurrencyCode(randomChoice(currencyCodes));
        product.setInterestRate(BigDecimal.valueOf(randomChoice(interestRates)));
        product.setLimit(BigDecimal.valueOf(new Random().nextDouble()));
        product.setDeletedStatus(DeletedStatus.ACTIVE);

        return productService.save(product);

    }

    private Credit generateCredit(UUID clientId) {
        Credit credit = new Credit();
        credit.setSumOfCredit(BigDecimal.valueOf(new Random().nextDouble()));
        credit.setClientId(clientId);
        credit.setNumberOfMonth(new Random().nextInt(1, 50));
        credit.setPaymentPerMonth(credit.getSumOfCredit().divide(
                BigDecimal.valueOf((credit.getNumberOfMonth())), 2, RoundingMode.HALF_UP));
        credit.setCreditType(randomChoice(creditTypes));
        credit.setCurrencyCode(randomChoice(currencyCodes));
        credit.setCreditStatus(randomChoice(creditStatuses));

        creditService.save(credit);

        return credit;
    }

    private Transaction generateTransaction(UUID debitAccountId) {
        Transaction transaction = new Transaction();
        transaction.setCreditAccountId(debitAccountId);
        transaction.setReceiverIban(ibanGen.generate());
        transaction.setType(randomChoice(transactionTypes));
        transaction.setAmount(BigDecimal.valueOf(new Random().nextDouble()));
        transaction.setDescription(randomChoice(transactionDescriptions));
        transaction.setSenderIBan(ibanGen.generate());

        return transactionService.save(transaction);

    }

    private Document generateDocument(UUID clientId) {
        Document document = new Document();
        document.setClientId(clientId);
        document.setPassport(generateBytes());
        document.setRegistration(generateBytes());

        return documentService.save(document);

    }

    private byte[] generateBytes() {
        Random random = new Random();
        byte[] byteArray = new byte[random.nextInt(500)];
        random.nextBytes(byteArray);
        return byteArray;
    }

    private <T> T randomChoice(List<T> list) {
        int choice = new Random().nextInt(list.size());
        return list.get(choice);
    }

    private String randomChoice(String path) {
        List<String> list = fileUtil.writeFromFileToList(path);
        return randomChoice(list);
    }
}