package com.banking.dbfiller;

import com.banking.entity.*;
import com.banking.entity.entityenumerations.*;
import com.banking.service.component.interfaces.IBanGeneratorComponent;
import com.banking.service.interfaces.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    List<String> firstNames = List.of(
            "Ivan", "John", "Maria", "David", "Anna", "Michael", "Sophia", "Daniel", "Olivia", "Peter",
            "Emma", "Alexander", "Isabella", "Andrew", "Mia", "Joseph", "Ava", "James", "Emily", "Nicholas",
            "Charlotte", "Christopher", "Amelia", "Matthew", "Abigail", "Anthony", "Harper", "Robert", "Elizabeth",
            "William", "Sofia", "Thomas", "Evelyn", "Charles", "Grace", "George", "Victoria", "Oliver", "Chloe",
            "Henry", "Scarlett", "Daniel", "Zoe", "Samuel", "Madison", "Benjamin", "Lily", "David", "Ella"
    );

    List<String> surnames = List.of(
            "Smith", "Johnson", "Brown", "Taylor", "Miller", "Wilson", "Davis", "Clark", "Hall", "Lewis",
            "Young", "Walker", "King", "Wright", "Green", "Hill", "Baker", "Campbell", "Carter", "Garcia",
            "Martinez", "Lopez", "Lee", "Gonzalez", "Nelson", "Moore", "Jackson", "White", "Harris", "Martin",
            "Thompson", "Garcia", "Robinson", "Clark", "Rodriguez", "Lewis", "Lee", "Walker", "Hall", "Allen",
            "Young", "Hill", "Wright", "Scott", "Turner", "Adams", "Baker", "Perez", "Parker"
    );

    List<ManagerStatus> managerStatusList = Arrays.asList(ManagerStatus.ACTIVE,
            ManagerStatus.INACTIVE, ManagerStatus.SUSPENDED, ManagerStatus.TERMINATED,
            ManagerStatus.ON_LEAVE, ManagerStatus.PROBATIONARY, ManagerStatus.PROMOTED,
            ManagerStatus.DEMOTED, ManagerStatus.TRANSFERRED, ManagerStatus.RETIRED
    );

    List<String> taxCodes = Arrays.asList(
            "TC001", "TC002", "TC003", "TC004", "TC005", "TC006", "TC007", "TC008", "TC009", "TC010",
            "TC011", "TC012", "TC013", "TC014", "TC015", "TC016", "TC017", "TC018", "TC019", "TC020",
            "TC021", "TC022", "TC023", "TC024", "TC025", "TC026", "TC027", "TC028", "TC029", "TC030",
            "TC031", "TC032", "TC033", "TC034", "TC035", "TC036", "TC037", "TC038", "TC039", "TC040",
            "TC041", "TC042", "TC043", "TC044", "TC045", "TC046", "TC047", "TC048", "TC049", "TC050"
    );

    List<String> phoneNumbers = Arrays.asList(
            "1234567890", "9876543210", "5555555555", "9998887777", "1112223333",
            "5551112222", "7778889999", "4443332222", "8889991111", "0001112222",
            "3336669999", "7770003333", "4442225555", "9994447777", "2223334444",
            "5550001111", "1112223333", "9991112222", "4445556666", "6667778888",
            "3336669999", "5554443333", "8887776666", "2221110000", "7778889999",
            "5556667777", "1112223333", "9998887777", "4443332222", "1110005555",
            "5551112222", "7778889999", "4443332222", "8889991111", "0001112222",
            "2223334444", "5550001111", "1112223333", "9991112222", "4445556666",
            "6667778888", "3336669999", "5554443333", "8887776666", "2221110000",
            "7778889999", "5556667777", "1112223333", "9998887777", "4443332222",
            "5551112222", "7778889999", "4443332222", "8889991111", "0001112222"
    );

    List<String> streetNames = List.of(
            "Main Street", "Park Avenue", "Broadway", "Oak Street", "Elm Street", "Maple Avenue", "Cedar Road",
            "Pine Street", "Sunset Boulevard", "Washington Avenue", "Lakeview Drive", "Highland Avenue",
            "Church Street", "Springfield Road", "River Street", "Smith Street", "Center Street", "Sunrise Avenue",
            "Hillside Drive", "Grove Street", "Meadow Lane", "Willow Court", "Forest Drive", "Chestnut Street",
            "Franklin Avenue", "Park Street", "Linden Avenue", "Orchard Lane", "Lincoln Street", "Hickory Lane",
            "Madison Avenue", "Rosewood Drive", "Beech Street", "Harrison Road", "Sycamore Lane", "College Street",
            "Mill Road", "Court Street", "Mulberry Lane", "Prospect Avenue", "Vine Street", "Canal Road",
            "Market Street", "University Avenue", "Cottage Lane", "Windsor Drive", "North Street",
            "Ridge Road", "School Lane", "Ash Street", "Meadow Street", "Holly Court"
    );

    List<String> accountNames = Arrays.asList(
            "Account1", "Account2", "Account3", "Account4", "Account5", "Account6", "Account7", "Account8",
            "Account9", "Account10", "Account11", "Account12", "Account13", "Account14", "Account15", "Account16",
            "Account17", "Account18", "Account19", "Account20", "Account21", "Account22", "Account23", "Account24",
            "Account25", "Account26", "Account27", "Account28", "Account29", "Account30", "Account31", "Account32",
            "Account33", "Account34", "Account35", "Account36", "Account37", "Account38", "Account39", "Account40",
            "Account41", "Account42", "Account43", "Account44", "Account45", "Account46", "Account47", "Account48",
            "Account49", "Account50"
    );

    List<AccountType> accountTypes = Arrays.asList(AccountType.values());
    List<AccountStatus> accountStatusList = Arrays.asList(AccountStatus.values());
    List<CurrencyCode> currencyCodes = Arrays.asList(CurrencyCode.values());
    List<AgreementStatus> agreementStatuses = Arrays.asList(AgreementStatus.values());
    List<DeletedStatus> deletedStatuses = Arrays.asList(DeletedStatus.values());
    List<ProductStatus> productStatuses = Arrays.asList(ProductStatus.values());
    List<CreditType> creditTypes = Arrays.asList(CreditType.values());
    List<CreditStatus> creditStatuses = Arrays.asList(CreditStatus.values());
    List<TransactionType> transactionTypes = Arrays.asList(TransactionType.values());
    List<Role> roles = Arrays.asList(Role.values());
    List<ClientStatus> clientStatuses = Arrays.asList(ClientStatus.values());

    List<Double> interestRates = List.of(0.5, 0.8, 1.2, 0.3, 0.9, 1.5, 1.0, 0.7, 0.6, 1.3);

    List<String> bankProductNames = Arrays.asList(
            "Savings Account", "Checking Account", "Credit Card",
            "Personal Loan", "Mortgage Loan", "Investment Account",
            "Term Deposit", "Auto Loan", "Business Loan", "Insurance"
    );

    List<String> transactionDescriptions = List.of("Grocery shopping", "Restaurant bill", "Online purchase",
            "Utility payment", "Movie ticket", "Coffee shop", "Gas station", "Clothing store", "Car rental",
            "Hotel booking", "Airline ticket", "Gym membership", "Home repair", "Electronics purchase", "Gift shopping",
            "Bookstore", "Music concert", "Theater show", "Bank transfer", "Tax payment", "Insurance premium",
            "Dentist appointment", "Doctor visit", "Pharmacy purchase", "Hair salon", "Spa treatment", "Hiking trip",
            "Car wash", "Mobile phone bill", "Internet service", "Credit card payment", "Video streaming subscription",
            "Fitness class", "Sports event", "Wedding gift", "Online donation", "School supplies", "Pet grooming",
            "Grocery delivery", "Home cleaning service", "Laundry service", "Movie rental", "Bakery purchase",
            "Furniture store", "Art exhibition", "Shopping mall", "Cinema ticket", "Travel agency", "Appliance repair",
            "Piano lessons", "Photography session"
    );


    public void fillAll() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            Manager manager = generateManager();
            for (int j = 0; j < 5; j++) {
                Client client = generateClient(manager.getId());
                Document document = generateDocument(client.getId());
                Product product = productGenerator(manager.getId());
                for (int k = 0; k < 5; k++) {
                    Account account = generateAccount(client.getId());
                    Credit credit = generateCredit(client.getId());
                    for (int l = 0; l < 5; l++) {
                        Agreement agreement = agreementGenerate(account.getId(), product.getId());
                        Transaction transaction = generateTransaction(account.getId());
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
}