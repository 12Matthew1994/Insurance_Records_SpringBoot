package insuranceRecords.data.entities;

import jakarta.persistence.*;

@Entity
public class InsuredEntity {



        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long insuredId;

        @Column(nullable = false)
        private String name;

        @Column(nullable = false)
        private String surname;

        @Column(nullable = false)
        private String city;

        @ManyToOne
        @JoinColumn(name = "insurance_Id")
        private InsuranceEntity insurance;

        @ManyToOne
        @JoinColumn(name = "user_Id")
        private InsuredEntity insured;


        public InsuredEntity getInsured() {
            return insured;
        }

        public void setInsured(InsuredEntity insured) {
            this.insured = insured;
        }

        public long getInsuredId() {
            return insuredId;
        }

        public void setInsuredId(long insuredId) {
            this.insuredId = insuredId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public InsuranceEntity getInsurance() {
            return insurance;
        }

        public void setInsurance(InsuranceEntity insurance) {
            this.insurance = insurance;
        }
    }
