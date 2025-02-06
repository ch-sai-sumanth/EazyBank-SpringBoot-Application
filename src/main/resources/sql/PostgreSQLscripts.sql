-- Drop tables if they exist
drop table if exists authorities;
drop table if exists users;
drop table if exists customer cascade;
drop table if exists accounts cascade;
drop table if exists account_transactions cascade;
drop table if exists loans cascade;
drop table if exists cards cascade;
drop table if exists notice_details cascade;

-- Create customer table
CREATE TABLE customer (
                          customer_id SERIAL PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          email VARCHAR(100) NOT NULL,
                          mobile_number VARCHAR(20) NOT NULL,
                          pwd VARCHAR(500) NOT NULL,
                          role VARCHAR(100) NOT NULL,
                          create_dt DATE DEFAULT NULL
);

INSERT INTO customer (name, email, mobile_number, pwd, role, create_dt)
VALUES ('Happy', 'happy@example.com', '5334122365', crypt('password', gen_salt('bf')), 'admin', CURRENT_DATE);

-- Create accounts table
CREATE TABLE accounts (
                          customer_id INT NOT NULL,
                          account_number BIGINT PRIMARY KEY,
                          account_type VARCHAR(100) NOT NULL,
                          branch_address VARCHAR(200) NOT NULL,
                          create_dt DATE DEFAULT NULL,
                          FOREIGN KEY (customer_id) REFERENCES customer (customer_id) ON DELETE CASCADE
);

INSERT INTO accounts (customer_id, account_number, account_type, branch_address, create_dt)
VALUES (1, 1865764534, 'Savings', '123 Main Street, New York', CURRENT_DATE);

-- Create account_transactions table
CREATE TABLE account_transactions (
                                      transaction_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                      account_number BIGINT NOT NULL,
                                      customer_id INT NOT NULL,
                                      transaction_dt DATE NOT NULL,
                                      transaction_summary VARCHAR(200) NOT NULL,
                                      transaction_type VARCHAR(100) NOT NULL,
                                      transaction_amt INT NOT NULL,
                                      closing_balance INT NOT NULL,
                                      create_dt DATE DEFAULT NULL,
                                      FOREIGN KEY (account_number) REFERENCES accounts (account_number) ON DELETE CASCADE,
                                      FOREIGN KEY (customer_id) REFERENCES customer (customer_id) ON DELETE CASCADE
);

INSERT INTO account_transactions (account_number, customer_id, transaction_dt, transaction_summary, transaction_type, transaction_amt, closing_balance, create_dt)
VALUES (1865764534, 1, CURRENT_DATE - INTERVAL '7 days', 'Coffee Shop', 'Withdrawal', 30, 34500, CURRENT_DATE - INTERVAL '7 days');

-- Create loans table
CREATE TABLE loans (
                       loan_number SERIAL PRIMARY KEY,
                       customer_id INT NOT NULL,
                       start_dt DATE NOT NULL,
                       loan_type VARCHAR(100) NOT NULL,
                       total_loan INT NOT NULL,
                       amount_paid INT NOT NULL,
                       outstanding_amount INT NOT NULL,
                       create_dt DATE DEFAULT NULL,
                       FOREIGN KEY (customer_id) REFERENCES customer (customer_id) ON DELETE CASCADE
);

INSERT INTO loans (customer_id, start_dt, loan_type, total_loan, amount_paid, outstanding_amount, create_dt)
VALUES (1, '2020-10-13', 'Home', 200000, 50000, 150000, '2020-10-13');

-- Create cards table
CREATE TABLE cards (
                       card_id SERIAL PRIMARY KEY,
                       card_number VARCHAR(100) NOT NULL,
                       customer_id INT NOT NULL,
                       card_type VARCHAR(100) NOT NULL,
                       total_limit INT NOT NULL,
                       amount_used INT NOT NULL,
                       available_amount INT NOT NULL,
                       create_dt DATE DEFAULT NULL,
                       FOREIGN KEY (customer_id) REFERENCES customer (customer_id) ON DELETE CASCADE
);

INSERT INTO cards (card_number, customer_id, card_type, total_limit, amount_used, available_amount, create_dt)
VALUES ('4565XXXX4656', 1, 'Credit', 10000, 500, 9500, CURRENT_DATE);

-- Create notice_details table
CREATE TABLE notice_details (
                                notice_id SERIAL PRIMARY KEY,
                                notice_summary VARCHAR(200) NOT NULL,
                                notice_details VARCHAR(500) NOT NULL,
                                notic_beg_dt DATE NOT NULL,
                                notic_end_dt DATE DEFAULT NULL,
                                create_dt DATE DEFAULT NULL,
                                update_dt DATE DEFAULT NULL
);

INSERT INTO notice_details (notice_summary, notice_details, notic_beg_dt, notic_end_dt, create_dt, update_dt)
VALUES ('Home Loan Interest rates reduced', 'Home loan interest rates are reduced as per government guidelines.', CURRENT_DATE - INTERVAL '30 days', CURRENT_DATE + INTERVAL '30 days', CURRENT_DATE, NULL);
