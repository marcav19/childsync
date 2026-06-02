import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: 'meals',
        loadComponent: () => { return import('./components/meal/meal').then(m => m.Meal) }
    },
    {
        path: 'sleep',
        loadComponent: () => { return import('./components/sleep/sleep').then(m => m.Sleep) }
    },
    {
        path: 'baths',
        loadComponent: () => { return import('./components/bath/bath').then(m => m.Bath) }
    },
    {
        path: 'potties',
        loadComponent: () => { return import('./components/potty/potty').then(m => m.Potty) }
    },
    {
        path: 'appointments',
        loadComponent: () => { return import('./components/appointment/appointment').then(m => m.Appointment) }
    },
    {
        path: 'medicine',
        loadComponent: () => { return import('./components/medicine/medicine').then(m => m.Medicine) }
    },
    {
        path: 'activities',
        loadComponent: () => { return import('./components/activity/activity').then(m => m.Activity) }
    }
];
