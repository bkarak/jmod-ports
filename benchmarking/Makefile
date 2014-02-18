JMOD_COMPILER=java -jar jmodv2.jar -javac
JAVA_COMPILER=javac
ITERATIONS=1000

benchmark:
	@echo 'Calculating compiler overhead (jmod-simple)'
	@-rm -f jmod-simple.measurements
	@count=${ITERATIONS} ; \
	while [ $$count -gt 0 ] ; \
	do \
		{ time $(JMOD_COMPILER) -i jmod-simple ; } 2> jmod-simple.tmp ; \
		cat jmod-simple.tmp | grep real >> jmod-simple.measurements ; \
		rm -f jmod-simple.tmp ; \
		count=`expr $$count - 1` ; \
		rm -rf work ; \
	done
	@echo 'Calculating compiler overhead (javac-compiler)'
	@-rm -f javac-compiler.measurements
	@count=${ITERATIONS} ; \
	while [ $$count -gt 0 ] ; \
	do \
		{ time $(JAVA_COMPILER) java/org/jmod/SimpleSQLProgram.java ; } 2> javac-compiler.tmp ; \
		cat javac-compiler.tmp | grep real >> javac-compiler.measurements ; \
		rm -f javac-compiler.tmp ; \
		count=`expr $$count - 1` ; \
	done

clean:
	-rm -f jmod-simple.measurements
	-rm -f jmod-simple.tmp
	-rm -f javac-compiler.measurements
	-rm -f javac-compiler.tmp
	-rm -rf work
	-rm -f velocity.log
