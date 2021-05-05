import dayjs from 'dayjs';
import { INextOfKin } from 'app/shared/model/next-of-kin.model';
import { IContact } from 'app/shared/model/contact.model';
import { RegistrationDocumentType } from 'app/shared/model/enumerations/registration-document-type.model';
import { Gender } from 'app/shared/model/enumerations/gender.model';

export interface IStudent {
  id?: number;
  firstName?: string;
  middleName?: string;
  lastName?: string;
  studentRegNumber?: string;
  dateOfBirth?: string;
  regDocType?: RegistrationDocumentType;
  registrationDocumentNumber?: string;
  gender?: Gender;
  nationality?: string | null;
  dateJoined?: string;
  deleted?: boolean | null;
  wxtJwtPq55wd?: string | null;
  relatives?: INextOfKin[];
  contacts?: IContact[] | null;
}

export const defaultValue: Readonly<IStudent> = {
  deleted: false,
};
