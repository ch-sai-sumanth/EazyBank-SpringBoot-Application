package com.easybytes.easybank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoadController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/load")
    public String loadData() {
        try {
            String sql = """
                CREATE EXTENSION IF NOT EXISTS pgcrypto;
                
                -- Insert into customer
                INSERT INTO customer (customer_id, name, email, mobile_number, pwd, role, create_dt)
                VALUES (1, 'Happy', 'happy@example.com', '5334122365', crypt('password', gen_salt('bf')), 'admin', CURRENT_DATE)
                ON CONFLICT (customer_id) DO NOTHING;

                -- Insert into authorities
                INSERT INTO authorities (id, customer_id, name)
                VALUES (1, 1, 'ROLE_ADMIN')
                ON CONFLICT (id) DO NOTHING;

                -- Insert into accounts
                INSERT INTO accounts (account_number, customer_id, account_type, branch_address, create_dt)
                VALUES (1865764534, 1, 'Savings', '123 Main Street, New York', CURRENT_DATE)
                ON CONFLICT (account_number) DO NOTHING;

                -- Insert into account_transactions
                INSERT INTO account_transactions (transaction_id, account_number, customer_id, transaction_dt, transaction_summary, transaction_type, transaction_amt, closing_balance, create_dt)
                VALUES ('TXN12345', 1865764534, 1, CURRENT_DATE - INTERVAL '7 days', 'Coffee Shop', 'Withdrawal', 30, 34500, CURRENT_DATE - INTERVAL '7 days')
                ON CONFLICT (transaction_id) DO NOTHING;

                -- Insert into loans
                INSERT INTO loans (loan_number, customer_id, start_dt, loan_type, total_loan, amount_paid, outstanding_amount, create_dt)
                VALUES (1001, 1, '2020-10-13', 'Home', 200000, 50000, 150000, '2020-10-13')
                ON CONFLICT (loan_number) DO NOTHING;

                -- Insert into cards
                INSERT INTO cards (card_id, card_number, customer_id, card_type, total_limit, amount_used, available_amount, create_dt)
                VALUES (2001, '4565XXXX4656', 1, 'Credit', 10000, 500, 9500, CURRENT_DATE)
                ON CONFLICT (card_id) DO NOTHING;

                -- Insert into notice_details
                INSERT INTO notice_details (notice_id, notice_summary, notice_details, notic_beg_dt, notic_end_dt, create_dt, update_dt)
                VALUES (3001, 'Home Loan Interest rates reduced', 'Home loan interest rates are reduced as per government guidelines.', CURRENT_DATE - INTERVAL '30 days', CURRENT_DATE + INTERVAL '30 days', CURRENT_DATE, NULL)
                ON CONFLICT (notice_id) DO NOTHING;

                -- Insert into contact_messages
                INSERT INTO contact_messages (contact_id, contact_name, contact_email, subject, message, create_dt)
                VALUES ('MSG1001', 'John Doe', 'johndoe@example.com', 'Loan Inquiry', 'I would like to know more about loan interest rates.', CURRENT_DATE)
                ON CONFLICT (contact_id) DO NOTHING;
            """;

            jdbcTemplate.execute(sql);
            return "Data loaded successfully!";
        } catch (Exception e) {
            return "Error loading data: " + e.getMessage();
        }
    }
}
