import { Component, OnInit } from '@angular/core';
import {
  faBuildingColumns,
  faHouse,
  faUsers,
  faFileLines
} from '@fortawesome/free-solid-svg-icons';
import { faCircleUser } from '@fortawesome/free-regular-svg-icons';

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.css']
})
export class SideBarComponent implements OnInit {
  faBuildingColumns = faBuildingColumns;
  faHouse = faHouse;
  faUsers = faUsers;
  faFileLines = faFileLines;
  faCircleUser = faCircleUser;

  constructor() {}

  ngOnInit(): void {}
}
