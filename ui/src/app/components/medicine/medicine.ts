import { Component, inject, signal } from '@angular/core';
import { MedicineService } from '../../services/medicine-service';
import { IMedicine } from '../../models/imedicine';
import { FormGroup, FormControl, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-medicine',
  imports: [ReactiveFormsModule],
  templateUrl: './medicine.html',
  styleUrl: './medicine.css',
})
export class Medicine {
  medicineService = inject(MedicineService);
  medicineData = signal<Array<IMedicine>>([]);
  selectedId = signal<number | null>(null);
  displayUpdateForm = signal(false);
  displayCreateForm = signal(false);
  displayDeleteConfirmation = signal(false);
  medicineForm = new FormGroup({
    medicineDateTime: new FormControl<string>(''),
    medicineName: new FormControl<string>(''),
    medicineDosage: new FormControl<string>(''),
    userId: new FormControl<number>(0)
  });
  
  ngOnInit() {
    this.loadMedicine();
  }

  findSelectedRow() {
    this.medicineService.getMedicineById(this.selectedId()!)
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

  loadMedicine() {
    this.medicineService.getAllMedicine()
                     .subscribe((s) => this.medicineData.set(s));
  }

  create() {
    const medicine = {
      'medicine_datetime' : (this.medicineForm.value.medicineDateTime!.replace('T', ' ') ?? '') + ':00',
      'medicine_name' : this.medicineForm.value.medicineName,
      'medicine_dosage' : this.medicineForm.value.medicineDosage,
      'user_id' : this.medicineForm.value.userId ?? 0
    };

    this.medicineService.createMedicine(medicine)
                     .subscribe(s => {
                       console.log('Entry created:', s);
                       this.displayCreateForm.set(false);
                       this.loadMedicine();
                     });
  }

  delete() {
    this.medicineService.deleteMedicine(this.selectedId()!)
                     .subscribe(s => {
                       console.log(s, 'deleted')
                       this.loadMedicine();
                     });
  }

  update() {
    const medicine = {
      'medicine_datetime' : (this.medicineForm.value.medicineDateTime!.replace('T', ' ') ?? '') + ':00',
      'medicine_name' : this.medicineForm.value.medicineName,
      'medicine_dosage' : this.medicineForm.value.medicineDosage,
      'user_id' : this.medicineForm.value.userId ?? 0
    };

    this.medicineService.updateMedicine(this.selectedId()!, medicine)
                     .subscribe(s => {
                       console.log('Entry updated:', s)
                       this.displayUpdateForm.set(false);
                       this.loadMedicine();
                     });
  }
}
