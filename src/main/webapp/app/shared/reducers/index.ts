import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale, { LocaleState } from './locale';
import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import student, {
  StudentState
} from 'app/entities/student/student.reducer';
// prettier-ignore
import employee, {
  EmployeeState
} from 'app/entities/employee/employee.reducer';
// prettier-ignore
import category, {
  CategoryState
} from 'app/entities/category/category.reducer';
// prettier-ignore
import item, {
  ItemState
} from 'app/entities/item/item.reducer';
// prettier-ignore
import address, {
  AddressState
} from 'app/entities/address/address.reducer';
// prettier-ignore
import contact, {
  ContactState
} from 'app/entities/contact/contact.reducer';
// prettier-ignore
import nextOfKin, {
  NextOfKinState
} from 'app/entities/next-of-kin/next-of-kin.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly locale: LocaleState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly student: StudentState;
  readonly employee: EmployeeState;
  readonly category: CategoryState;
  readonly item: ItemState;
  readonly address: AddressState;
  readonly contact: ContactState;
  readonly nextOfKin: NextOfKinState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  locale,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  student,
  employee,
  category,
  item,
  address,
  contact,
  nextOfKin,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar,
});

export default rootReducer;
