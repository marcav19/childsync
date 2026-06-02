import { Component, inject, signal } from '@angular/core';
import { MealService } from '../../services/meal-service';
import { IMeal } from '../../models/imeal';
import { FormGroup, FormControl, ReactiveFormsModule } from '@angular/forms';
import { UserService } from '../../services/user-service';
import { IUser } from '../../models/iuser';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-meal',
  imports: [ReactiveFormsModule, DatePipe],
  templateUrl: './meal.html',
  styleUrl: './meal.css',
})
export class Meal {
  mealService = inject(MealService);
  userService = inject(UserService);
  meals = signal<Array<IMeal>>([]);
  users = signal<Array<IUser>>([]);
  selectedMeal = signal<number | null>(null);
  displayUpdateForm = signal(false);
  displayCreateForm = signal(false);
  displayDeleteConfirmation = signal(false);
  isSelected = signal(true);
  mealForm = new FormGroup({
    mealDateTime: new FormControl<string>(''),
    mealName: new FormControl<string>(''),
    mealComment: new FormControl<string>(''),
    userId: new FormControl<number>(1)
  });
    
  ngOnInit() {
    this.loadMeals();
    this.loadUsers();
  }
  
  findSelectedRow() {
    this.mealService.getMealById(this.selectedMeal()!)
                    .subscribe(s => {
                        s.dateTime = s.dateTime.slice(0, 16);
                        this.mealForm.patchValue({
                          mealDateTime: s.dateTime,
                          mealName: s.name,
                          mealComment: s.comment,
                          userId: s.userId
                        });
                    });
  }

  toggleCreateForm() {
    this.displayUpdateForm.set(false);
    this.displayDeleteConfirmation.set(false);
    this.mealForm.reset();
    this.displayCreateForm.set(true);
  }
  
  toggleUpdateForm() {
    this.displayCreateForm.set(false);
    this.displayDeleteConfirmation.set(false);
    this.findSelectedRow();
    this.displayUpdateForm.set(true);
  }
  
  toggleDeleteConfirmation() {
    this.displayCreateForm.set(false);
    this.displayUpdateForm.set(false);
    this.displayDeleteConfirmation.set(true);
  }
  
  loadMeals() {
    this.mealService.getAllMeals()
                    .subscribe(s => this.meals.set(s));
  }

  loadUsers() {
    this.userService.getAllUsers()
                    .subscribe(s => this.users.set(s));
  }
  
  create() {
    const meal = {
      'dateTime' : this.mealForm.value.mealDateTime + ':00',
      'name' : this.mealForm.value.mealName,
      'comment' : this.mealForm.value.mealComment,
      'userId' : this.mealForm.value.userId
    };
  
    this.mealService.createMeal(meal)
                    .subscribe(s => {
                        console.log('Entry created:', s);
                        this.displayCreateForm.set(false);
                        this.loadMeals();
                    });
  }
  
  delete() {
    this.mealService.deleteMeal(this.selectedMeal()!)
                    .subscribe(s => {
                        console.log('Entry deleted')
                        this.loadMeals();
                    });
  }
  
  update() {
    const meal = {
      'dateTime' : this.mealForm.value.mealDateTime + ':00',
      'name' : this.mealForm.value.mealName,
      'comment' : this.mealForm.value.mealComment,
      'userId' : this.mealForm.value.userId
    };
  
    this.mealService.updateMeal(this.selectedMeal()!, meal)
                    .subscribe(s => {
                        console.log('Entry updated:', s)
                        this.displayUpdateForm.set(false);
                        this.loadMeals();
                    });
  }
}
