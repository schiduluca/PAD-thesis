CREATE TABLE customers(
  id SERIAL PRIMARY KEY,
  name TEXT,
  surname TEXT,
  age NUMERIC,
  email TEXT UNIQUE NOT NULL,
  password TEXT NOT NULL,
  company_id INTEGER NOT NULL,
  FOREIGN KEY (company_id) REFERENCES companies(id)
);
