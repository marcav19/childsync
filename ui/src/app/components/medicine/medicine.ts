import { Component, inject, signal } from '@angular/core';
import { MedicineService } from '../../services/medicine-service';
import { IMedicine } from '../../models/imedicine';
import { FormGroup, FormControl, ReactiveFormsModule } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { UserService } from '../../services/user-service';
import { IUser } from '../../models/iuser';

@Component({
  selector: 'app-medicine',
  imports: [ReactiveFormsModule, DatePipe],
  templateUrl: './medicine.html',
  styleUrl: './medicine.css',
})
export class Medicine {
  medicineService = inject(MedicineService);
  userService = inject(UserService);
  medicines = signal<Array<IMedicine>>([]);
  users = signal<Array<IUser>>([]);
  selectedMedicine = signal<number | null>(null);
  displayUpdateForm = signal(false);
  displayCreateForm = signal(false);
  displayDeleteConfirmation = signal(false);
  isSelected = signal(true);
  medicineForm = new FormGroup({
    medicineDateTime: new FormControl<string>(''),
    medicineName: new FormControl<string>(''),
    medicineDosage: new FormControl<string>(''),
    userId: new FormControl<number>(1)
  });
  
  ngOnInit() {
    this.loadMedicines();
    this.loadUsers();
  }

  findSelectedRow() {
    this.medicineService.getMedicineById(this.selectedMedicine()!)
                        .subscribe(s => {
                          s.dateTime = s.dateTime.slice(0, 16);
                          this.medicineForm.patchValue({
                            medicineDateTime: s.dateTime,
                            medicineName: s.name,
                            medicineDosage: s.dosage,
                            userId: s.userId
                          });
                        });
  }

  toggleCreateForm() {
    this.displayUpdateForm.set(false);
    this.displayDeleteConfirmation.set(false);
    this.medicineForm.reset();
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

  loadMedicines() {
    this.medicineService.getAllMedicines()
                        .subscribe(s => this.medicines.set(s));
  }

  loadUsers() {
    this.userService.getAllUsers()
                    .subscribe(s => this.users.set(s));
  }

  create() {
    const medicine = {
      'dateTime' : this.medicineForm.value.medicineDateTime + ':00',
      'name' : this.medicineForm.value.medicineName,
      'dosage' : this.medicineForm.value.medicineDosage,
      'userId' : this.medicineForm.value.userId
    };

    this.medicineService.createMedicine(medicine)
                        .subscribe(s => {
                          console.log('Entry created:', s);
                          this.displayCreateForm.set(false);
                          this.loadMedicines();
                        });
  }

  delete() {
    this.medicineService.deleteMedicine(this.selectedMedicine()!)
                        .subscribe(s => {
                          console.log('Entry deleted')
                          this.displayDeleteConfirmation.set(false);
                          this.loadMedicines();
                        });
  }

  update() {
    const medicine = {
      'dateTime' : this.medicineForm.value.medicineDateTime + ':00',
      'name' : this.medicineForm.value.medicineName,
      'dosage' : this.medicineForm.value.medicineDosage,
      'userId' : this.medicineForm.value.userId
    };

    this.medicineService.updateMedicine(this.selectedMedicine()!, medicine)
                        .subscribe(s => {
                          console.log('Entry updated:', s)
                          this.displayUpdateForm.set(false);
                          this.loadMedicines();
                        });
  }
}
