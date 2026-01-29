package br.com.a4a.subpay.application.port.outbound;

import br.com.a4a.subpay.domain.entity.Account;
import br.com.a4a.subpay.domain.vo.AccountId;

import java.time.LocalDateTime;
import java.util.Optional;

// Output port for fetching accounts from the data source
// The role of output port is to describe, in a technology-agnostic way,
// the operations that can be performed on the data source (Database, external API, File System and so on).
// We say agnostic because output ports don't care whether the data comes from a particular
// relational database technology or filesystem for example.
// We assign this responsibility to output adapters.
public interface LoadAccountPort {
    Optional<Account> loadAccount(AccountId accountId, LocalDateTime baselineDate);
}
