import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AutorComponent } from './autor';

describe('Autor', () => {
  let component: AutorComponent;
  let fixture: ComponentFixture<AutorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AutorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AutorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
