import { Component, inject, signal } from '@angular/core';
import { MealService } from '../../services/meal-service';
import { IMeal } from '../../models/imeal';
import { FormGroup, FormControl, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-meal',
  imports: [ReactiveFormsModule],
  templateUrl: './meal.html',
  styleUrl: './meal.css',
})
export class Meal {
  mealService = inject(MealService);
  mealData = signal<Array<IMeal>>([]);
  selectedId = signal<number | null>(null);
  displayUpdateForm = signal(false);
  displayCreateForm = signal(false);
  displayDeleteConfirmation = signal(false);
  mealForm = new FormGroup({
    mealDateTime: new FormControl<string>(''),
    mealName: new FormControl<string>(''),
    mealComment: new FormControl<string>(''),
    userId: new FormControl<number>(0)
  });
    
  ngOnInit() {
    this.loadMeal();
  }
  
  findSelectedRow() {
    this.mealService.getMealById(this.selectedId()!)
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
  
  loadMeal() {
    this.mealService.getAllMeal()
                     .subscribe((s) => this.mealData.set(s));
  }
  
  create() {
    const meal = {
      'meal_datetime' : (this.mealForm.value.mealDateTime!.replace('T', ' ') ?? '') + ':00',
      'meal_name' : this.mealForm.value.mealName,
      'meal_comment' : this.mealForm.value.mealComment,
      'user_id' : this.mealForm.value.userId ?? 0
    };
  
    this.mealService.createMeal(meal)
                     .subscribe(s => {
                       console.log('Entry created:', s);
                       this.displayCreateForm.set(false);
                       this.loadMeal();
                     });
  }
  
  delete() {
    this.mealService.deleteMeal(this.selectedId()!)
                     .subscribe(s => {
                       console.log(s, 'deleted')
                       this.loadMeal();
                     });
  }
  
  update() {
    const meal = {
      'meal_datetime' : (this.mealForm.value.mealDateTime!.replace('T', ' ') ?? '') + ':00',
      'meal_name' : this.mealForm.value.mealName,
      'meal_comment' : this.mealForm.value.mealComment,
      'user_id' : this.mealForm.value.userId ?? 0
    };
  
    this.mealService.updateMeal(this.selectedId()!, meal)
                     .subscribe(s => {
                       console.log('Entry updated:', s)
                       this.displayUpdateForm.set(false);
                       this.loadMeal();
                     });
  }
}
