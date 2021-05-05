import dayjs from 'dayjs';
import { IContact } from 'app/shared/model/contact.model';
import { IEmployee } from 'app/shared/model/employee.model';
import { IStudent } from 'app/shared/model/student.model';
import { RegistrationDocumentType } from 'app/shared/model/enumerations/registration-document-type.model';
import { Gender } from 'app/shared/model/enumerations/gender.model';

export interface INextOfKin {
  id?: number;
  firstName?: string;
  middleName?: string;
  lastName?: string;
  dateOfBirth?: string;
  regDocType?: RegistrationDocumentType;
  registrationDocumentNumber?: string;
  gender?: Gender;
  nationality?: string | null;
  deleted?: boolean | null;
  wxtJwtPq55wd?: string | null;
  relation?: string;
  contacts?: IContact[] | null;
  employees?: IEmployee[] | null;
  students?: IStudent[] | null;
}

export const defaultValue: Readonly<INextOfKin> = {
  deleted: false,
};
