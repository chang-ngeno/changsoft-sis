import dayjs from 'dayjs';
import { ICategory } from 'app/shared/model/category.model';

export interface IItem {
  id?: number;
  name?: string;
  code?: string;
  description?: string | null;
  dateSince?: string | null;
  dateUpto?: string | null;
  deleted?: boolean | null;
  category?: ICategory | null;
}

export const defaultValue: Readonly<IItem> = {
  deleted: false,
};
