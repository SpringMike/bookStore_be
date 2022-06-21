package com.example.demo.model;
import com.example.demo.dto.FeaturedBookDTO;
import com.example.demo.dto.TransactionDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedNativeQuery(
        name = "getTransactionHistory",
        query = "select transaction_history.id,\n" +
                "       o.total as amount,\n" +
                "       transaction_history.bank_code    as bankCode,\n" +
                "       transaction_history.bank_tran_no as bankTranNo,\n" +
                "       transaction_history.card_type    as cardType,\n" +
                "       transaction_history.order_info   as orderInfo,\n" +
                "       o.create_date                    as createDate,\n" +
                "       a.full_name                      as accountName,\n" +
                "       transaction_history.order_id     as orderId\n" +
                "from [transaction_history]\n" +
                "         inner join [order] o on o.id = transaction_history.order_id\n" +
                "         inner join account a on a.id = o.account_id",
        resultSetMapping = "FeaturedTransactionHistoryMapping"
)
@SqlResultSetMapping(
        name = "FeaturedTransactionHistoryMapping",
        classes = {
                @ConstructorResult(
                        targetClass = TransactionDTO.class,
                        columns = {
                                @ColumnResult(name="id", type = Long.class),
                                @ColumnResult(name="amount", type = Float.class),
                                @ColumnResult(name="bankCode", type = String.class),
                                @ColumnResult(name="bankTranNo", type = String.class),
                                @ColumnResult(name="cardType", type = String.class),
                                @ColumnResult(name="orderInfo", type = String.class),
                                @ColumnResult(name="createDate", type = String.class),
                                @ColumnResult(name="accountName", type = String.class),
                                @ColumnResult(name="orderId", type = Long.class),
                        }
                )
        }
)
public class TransactionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float amount;
    private String bankCode;
    private String bankTranNo;
    private String cardType;
    private String payDate;
    private String orderInfo;
    private String responseCode;
    private String secureHashType;
    private String secureHash;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderId",insertable = false,updatable = false)
    private Order order;
    private Long orderId;


}
