import { Component } from '@angular/core';
import { Sleep } from "../components/sleep/sleep";

@Component({
  selector: 'app-home',
  imports: [Sleep],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {

}
