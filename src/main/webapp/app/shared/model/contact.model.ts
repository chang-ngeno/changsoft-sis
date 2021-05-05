import { IEmployee } from 'app/shared/model/employee.model';
import { INextOfKin } from 'app/shared/model/next-of-kin.model';
import { IStudent } from 'app/shared/model/student.model';

export interface IContact {
  id?: number;
  email?: string;
  mobileNumber?: string;
  deleted?: boolean | null;
  employee?: IEmployee | null;
  nextOfKin?: INextOfKin | null;
  student?: IStudent | null;
}

export const defaultValue: Readonly<IContact> = {
  deleted: false,
};
