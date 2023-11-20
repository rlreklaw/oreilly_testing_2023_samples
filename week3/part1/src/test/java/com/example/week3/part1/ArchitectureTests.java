package com.example.week3.part1;

import com.example.week3.part1.applier.DiscountApplier;
import com.example.week3.part1.repository.RateRepository;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchTests;
import com.tngtech.archunit.lang.ArchRule;
import org.w3c.dom.ls.LSOutput;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

@AnalyzeClasses(packages = "com.example.week3.part1")
public class ArchitectureTests {
	private ArchitectureTests() {}

	@ArchTest
	static final ArchTests modelTests = ArchTests.in(ModelTests.class);

	@ArchTest
	static final ArchTests repositoryTests = ArchTests.in(RepositoryTests.class);

	@ArchTest
	static final ArchTests applierTests = ArchTests.in(ApplierTests.class);

	@ArchTest
	static final ArchRule layerTest =
			layeredArchitecture()
					.consideringOnlyDependenciesInLayers()
					.layer("Applier").definedBy("..applier..")
					.layer("Model").definedBy("..model..")
					.layer("Repository").definedBy("..repository..")

					.whereLayer("Applier").mayNotBeAccessedByAnyLayer()
					.whereLayer("Repository").mayOnlyBeAccessedByLayers("Applier")
					.whereLayer("Model").mayNotAccessAnyLayer();

	@ArchTest
	static final ArchRule packageCycleTest =
			slices().matching("com.example.week3.part1.(*)..")
					.should().beFreeOfCycles();


	static class ModelTests {
		private ModelTests() {}

		@ArchTest
		static final ArchRule modelClassesShouldBePublic =
				classes().that().resideInAPackage("..model..")
						.should().bePublic();

		@ArchTest
		static final ArchRule modelClassesShouldNotDependOnOtherPackages =
				classes().that().resideInAPackage("..model..")
						.should().onlyDependOnClassesThat().resideInAnyPackage("..java..", "..model..");
	}

	static class RepositoryTests {

		@ArchTest
		static final ArchRule  repositoryCannotDependOnApplier =
				noClasses().that().resideInAPackage("..repository..")
						.should().dependOnClassesThat().resideInAPackage("..applier..");

		@ArchTest
		static final ArchRule repositoryShouldHaveAProperSuffixAndResideInProperPackage =
		        classes().that().implement(RateRepository.class)
						.should().haveSimpleNameEndingWith("RateRepository")
						.andShould().resideInAPackage("..repository..");

		@ArchTest
		static final ArchRule repositoryShouldHavePublicInterfaces =
		        classes().that().areInterfaces().and().resideInAPackage("..repository..")
						.should().bePublic();

		@ArchTest
		static final ArchRule repositoryShouldHavePackagePrivateClasses =
		        classes().that().areNotInterfaces().and().resideInAPackage("..repository..")
						.should().bePackagePrivate();

	}

	static class ApplierTests {
		@ArchTest
		static final ArchRule applierCanDependOnModelAndRepository =
				classes().that().resideInAPackage("..applier..")
						.should().onlyDependOnClassesThat()
						.resideInAnyPackage("..repository..", "..model..", "..applier..", "..java..");
		;
		
		@ArchTest
		static final ArchRule appliersShouldHaveAProperSuffixAndResideInProperPackage  =
				classes().that().implement(DiscountApplier.class)
						.should().haveSimpleNameEndingWith("DiscountApplier")
						.andShould().resideInAPackage("..applier..");

		@ArchTest
		static final ArchRule appliersShouldHavePublicInterfaces  =
				classes().that().areInterfaces().and().resideInAPackage("..applier..")
						.should().bePublic();


		@ArchTest
		static final ArchRule appliersShouldHavePackagePrivateClasses  =
				classes().that().areNotInterfaces().and().resideInAPackage("..applier..")
						.should().bePackagePrivate();
		
	}

}
