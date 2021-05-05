import { IStudent } from 'app/shared/model/student.model';
import { IEmployee } from 'app/shared/model/employee.model';
import { INextOfKin } from 'app/shared/model/next-of-kin.model';

export interface IAddress {
  id?: number;
  houseNumber?: string | null;
  streetAddress?: string | null;
  county?: string;
  district?: string;
  cityTown?: string;
  postalCode?: number | null;
  deleted?: boolean | null;
  student?: IStudent | null;
  employee?: IEmployee | null;
  nextOfKin?: INextOfKin | null;
}

export const defaultValue: Readonly<IAddress> = {
  deleted: false,
};
