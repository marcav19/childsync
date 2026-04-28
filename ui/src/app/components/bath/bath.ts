import { Component, inject, signal } from '@angular/core';
import { BathService } from '../../services/bath-service';
import { IBath } from '../../models/ibath';
import { FormGroup, FormControl, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-bath',
  imports: [ReactiveFormsModule],
  templateUrl: './bath.html',
  styleUrl: './bath.css',
})
export class Bath {
  bathService = inject(BathService);
  bathData = signal<Array<IBath>>([]);
  selectedId = signal<number | null>(null);
  displayUpdateForm = signal(false);
  displayCreateForm = signal(false);
  displayDeleteConfirmation = signal(false);
  bathForm = new FormGroup({
    bathDateTime: new FormControl<string>(''),
    userId: new FormControl<number>(0)
  });
    
  ngOnInit() {
    this.loadBath();
  }
  
  findSelectedRow() {
    this.bathService.getBathById(this.selectedId()!)
                     .subscribe(s => {
                        s.dateTime = s.dateTime.slice(0, 16);
                        this.bathForm.patchValue({
                          bathDateTime: s.dateTime,
                          userId: s.userId
                        });
                     });
  }
  
  toggleCreateForm() {
    this.displayUpdateForm.set(false);
    this.displayDeleteConfirmation.set(false);
    this.bathForm.reset();
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
  
  loadBath() {
    this.bathService.getAllBath()
                     .subscribe((s) => this.bathData.set(s));
  }
  
  create() {
    const bath = {
      'bath_datetime' : (this.bathForm.value.bathDateTime!.replace('T', ' ') ?? '') + ':00',
      'user_id' : this.bathForm.value.userId ?? 0
    };
  
    this.bathService.createBath(bath)
                     .subscribe(s => {
                       console.log('Entry created:', s);
                       this.displayCreateForm.set(false);
                       this.loadBath();
                     });
  }
  
  delete() {
    this.bathService.deleteBath(this.selectedId()!)
                     .subscribe(s => {
                       console.log(s, 'deleted')
                       this.loadBath();
                     });
  }
  
  update() {
    const bath = {
      'bath_datetime' : (this.bathForm.value.bathDateTime!.replace('T', ' ') ?? '') + ':00',
      'user_id' : this.bathForm.value.userId ?? 0
    };
  
    this.bathService.updateBath(this.selectedId()!, bath)
                     .subscribe(s => {
                       console.log('Entry updated:', s)
                       this.displayUpdateForm.set(false);
                       this.loadBath();
                     });
  }
}
