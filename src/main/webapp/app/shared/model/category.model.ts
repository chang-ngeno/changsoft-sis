import { IItem } from 'app/shared/model/item.model';

export interface ICategory {
  id?: number;
  name?: string;
  code?: string;
  description?: string | null;
  deleted?: boolean | null;
  items?: IItem[] | null;
}

export const defaultValue: Readonly<ICategory> = {
  deleted: false,
};
