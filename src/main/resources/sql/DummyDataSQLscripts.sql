INSERT INTO customer (name, email, mobile_number, pwd, role, create_dt)
VALUES ('Happy', 'happy@example.com', '5334122365', crypt('password', gen_salt('bf')), 'admin', CURRENT_DATE);

INSERT INTO accounts (customer_id, account_number, account_type, branch_address, create_dt)
VALUES (1, 1865764534, 'Savings', '123 Main Street, New York', CURRENT_DATE);

INSERT INTO account_transactions (account_number, customer_id, transaction_dt, transaction_summary, transaction_type, transaction_amt, closing_balance, create_dt)
VALUES (1865764534, 1, CURRENT_DATE - INTERVAL '7 days', 'Coffee Shop', 'Withdrawal', 30, 34500, CURRENT_DATE - INTERVAL '7 days');

INSERT INTO loans (customer_id, start_dt, loan_type, total_loan, amount_paid, outstanding_amount, create_dt)
VALUES (1, '2020-10-13', 'Home', 200000, 50000, 150000, '2020-10-13');

INSERT INTO cards (card_number, customer_id, card_type, total_limit, amount_used, available_amount, create_dt)
VALUES ('4565XXXX4656', 1, 'Credit', 10000, 500, 9500, CURRENT_DATE);

INSERT INTO notice_details (notice_summary, notice_details, notic_beg_dt, notic_end_dt, create_dt, update_dt)
VALUES ('Home Loan Interest rates reduced', 'Home loan interest rates are reduced as per government guidelines.', CURRENT_DATE - INTERVAL '30 days', CURRENT_DATE + INTERVAL '30 days', CURRENT_DATE, NULL);
