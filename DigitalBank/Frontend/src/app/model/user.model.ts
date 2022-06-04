export interface UserRole {
  id: number;
  roleName: string;
}

export interface User {
  id: number;
  username: string;
  appRoles: UserRole[];
}
