import { Component } from '@angular/core';
import { Menu } from '../components/menu/menu';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-home',
  imports: [Menu, RouterOutlet],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {

}
