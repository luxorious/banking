package com.banking.businesslogic.implementation;

import com.banking.businesslogic.interfaces.Payment;
import com.banking.entity.Account;
import com.banking.entity.Transaction;
import com.banking.repository.AccountRepository;
import com.banking.service.interfaces.utility.GetEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Component
public class OnlinePayImpl implements Payment {
    private final AccountRepository accountRepository;
    private final Transaction transaction;
    private final GetEntity<Account> getAccount;

    @Override
    public Account accountValidation(UUID id) {
        Optional<Account> accountFromDB = accountRepository.findAccountById(id);
        return getAccount.getEntity(accountFromDB);

        //чи потрібно мені ці декілька рядків коду, якщо чисто фізично не можу отримати нулл?
        // чи я щось не правильно розумію? якщо усі виклики методів будуть реалізвоані через контролер,
        // то чи варто робити в іншому місці обробники виключень, чи можна зробити лише у контролері
        // з відповідною анотацією? і цього буде досить? якщо я правильно розумію, то у контролері буде
        // вискакувати виключення коли 'користувач' перейде за посиланням, чи як ці виключення працюють?

    }


//    на скільки я розумію, тут є порушення 1 принципу солід, бо метод отримує
//    дані звідкись і зрівнює їх а потім записує і плюс до всього формує транзакцію, я правильно розумію?
    @Override
    @Transactional//цю анотацію можна класти на контролер, щоб не було конфліктів, чи краще щоб вона була у сервісах?
    public void pay(UUID id, BigDecimal sum) {
        Account accountFromDB = accountValidation(id);
        if (accountFromDB.getBalance() != null && accountFromDB.getBalance().compareTo(sum) > 0){
            accountFromDB.setBalance(accountFromDB.getBalance().subtract(sum));
            accountRepository.save(accountFromDB);
            saveTransaction();
            log.info("payment successful");
        } else {
            log.info("there is not enough money in the account");
        }
    }
    //De morgan rule


    public void saveTransaction(){
        //dosomethig
    }
}
