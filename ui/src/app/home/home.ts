import { Component } from '@angular/core';
import { Sleep } from "../components/sleep/sleep";
import { Menu } from '../components/menu/menu';

@Component({
  selector: 'app-home',
  imports: [Menu, Sleep],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {

}
