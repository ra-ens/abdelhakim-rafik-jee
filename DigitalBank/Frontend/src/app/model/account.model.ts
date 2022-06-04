import { Customer } from './customer.model';

export interface AccountDetails {
  currentPage: number;
  totalPages: number;
  size: number;
  bankAccount: BankAccount;
  operations: AccountOperation[];
}

export interface BankAccount {
  id: string;
  type: string;
  balance: number;
  currency?: any;
  status?: any;
  customer: Customer;
  interestRate: number;
  createdAt: Date;
}

export interface AccountOperation {
  id: number;
  type: string;
  amount: number;
  description: string;
  date: Date;
}
